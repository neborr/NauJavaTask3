package ru.vlad.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.vlad.NauJava.entity.Movie;
import ru.vlad.NauJava.repository.custom.CustomMovieRepository;
import java.util.List;

@Repository
@RepositoryRestResource(path = "movies")
public interface MovieRepository extends CrudRepository<Movie, Long>, CustomMovieRepository {
    List<Movie> findByTitleAndGenre(String title, String genre);
    List<Movie> findByDurationMinBetween(int start, int end);
}