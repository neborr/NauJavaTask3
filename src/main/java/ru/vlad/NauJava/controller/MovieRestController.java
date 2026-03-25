package ru.vlad.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vlad.NauJava.entity.Movie;
import ru.vlad.NauJava.entity.Ticket;
import ru.vlad.NauJava.repository.MovieRepository;
import java.util.List;

@RestController
@RequestMapping("/custom/movies")
public class MovieRestController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieRestController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/search")
    public List<Movie> search(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String genre) {
        return movieRepository.findMoviesByCriteria(title, genre);
    }

    @GetMapping("/tickets-by-movie")
    public List<Ticket> getTicketsByMovie(@RequestParam String movieTitle) {
        return movieRepository.findTicketsByMovieTitleCriteria(movieTitle);
    }
}