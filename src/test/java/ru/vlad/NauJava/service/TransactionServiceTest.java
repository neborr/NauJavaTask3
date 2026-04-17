package ru.vlad.NauJava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vlad.NauJava.entity.*;
import ru.vlad.NauJava.repository.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testBookingSuccess() {
        User user = new User();
        user.setFullName("Некрасов Владислав Андреевич");
        user.setEmail("vlad@gmail.com");
        user = userRepository.save(user);

        Movie movie = new Movie();
        movie.setTitle("Интерстеллар");
        movie.setGenre("Научная фантастика");
        movie.setDurationMin(169);
        movie = movieRepository.save(movie);

        Session session = new Session();
        session.setMovie(movie);
        session = sessionRepository.save(session);

        Long realSessionId = session.getId();
        Long realUserId = user.getId();

        assertDoesNotThrow(() ->
                transactionService.executeBookingTransaction(realSessionId, realUserId, 5, 12)
        );
    }

    @Test
    void testBookingRollback() {
        Long nonExistentSessionId = 9999L;
        long countBefore = ticketRepository.count();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionService.executeBookingTransaction(nonExistentSessionId, 1L, 10, 5);
        });

        assertEquals("Сеанс не найден!", exception.getMessage());

        long countAfter = ticketRepository.count();
        assertEquals(countBefore, countAfter, "Билет не должен был создаться");
    }
}