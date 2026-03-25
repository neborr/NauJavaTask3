package ru.vlad.NauJava.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vlad.NauJava.entity.*;
import java.util.ArrayList;
import java.util.List;

public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @GetMapping("/search")
    public List<Movie> findMoviesByCriteria(String title, String genre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> movie = query.from(Movie.class);

        List<Predicate> predicates = new ArrayList<>();
        if (title != null) predicates.add(cb.equal(movie.get("title"), title));
        if (genre != null) predicates.add(cb.equal(movie.get("genre"), genre));

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    @GetMapping("/tickets")
    public List<Ticket> findTicketsByMovieTitleCriteria(String movieTitle) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = cb.createQuery(Ticket.class);

        Root<Ticket> ticket = query.from(Ticket.class);

        Join<Ticket, Session> sessionJoin = ticket.join("session");
        Join<Session, Movie> movieJoin = sessionJoin.join("movie");

        query.select(ticket)
                .where(cb.equal(movieJoin.get("title"), movieTitle));

        return entityManager.createQuery(query).getResultList();
    }
}