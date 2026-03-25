package ru.vlad.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlad.NauJava.repository.MovieRepository;

@Controller
@RequestMapping("/view/movies")
public class MovieViewController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/list")
    public String showMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movieList";
    }
}