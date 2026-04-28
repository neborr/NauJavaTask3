package ru.vlad.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlad.NauJava.entity.Movie;
import ru.vlad.NauJava.repository.MovieRepository;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> searchMovies(String title, String genre) {
        return movieRepository.findMoviesByCriteria(title, genre);
    }
}