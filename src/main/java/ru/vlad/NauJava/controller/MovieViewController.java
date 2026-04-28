package ru.vlad.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlad.NauJava.service.MovieService;

@Controller
@RequestMapping("/view/movies")
public class MovieViewController {

    private final MovieService movieService;

    @Autowired
    public MovieViewController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/list")
    public String showMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movieList";
    }
}