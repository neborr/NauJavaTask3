package ru.vlad.NauJava.repository.custom;

import ru.vlad.NauJava.entity.Movie;
import ru.vlad.NauJava.entity.Ticket;
import java.util.List;

public interface CustomMovieRepository {
    List<Movie> findMoviesByCriteria(String title, String genre);

    List<Ticket> findTicketsByMovieTitleCriteria(String movieTitle);
}