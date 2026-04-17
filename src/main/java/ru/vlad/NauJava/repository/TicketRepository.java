package ru.vlad.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vlad.NauJava.entity.Ticket;
import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.session.movie.title = :movieTitle")
    List<Ticket> findAllByMovieTitle(@Param("movieTitle") String movieTitle);
}