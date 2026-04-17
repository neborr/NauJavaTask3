package ru.vlad.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vlad.NauJava.entity.Movie;
import ru.vlad.NauJava.repository.custom.CustomMovieRepository;
import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, CustomMovieRepository {

    List<Movie> findByTitleAndGenre(String title, String genre);

    List<Movie> findByDurationMinBetween(int start, int end);
}