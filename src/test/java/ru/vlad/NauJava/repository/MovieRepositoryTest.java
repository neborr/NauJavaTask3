package ru.vlad.NauJava.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.vlad.NauJava.entity.Movie;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testFindByTitleAndGenre() {
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-Fi");
        movieRepository.save(movie);

        List<Movie> found = movieRepository.findByTitleAndGenre("Interstellar", "Sci-Fi");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getTitle()).isEqualTo("Interstellar");
    }

    @Test
    void testFindMoviesByCriteria() {
        Movie movie = new Movie();
        movie.setTitle("The Matrix");
        movie.setGenre("Action");
        movieRepository.save(movie);

        List<Movie> found = movieRepository.findMoviesByCriteria("The Matrix", "Action");

        assertThat(found).isNotEmpty();
    }
}