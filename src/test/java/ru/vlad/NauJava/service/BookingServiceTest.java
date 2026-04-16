package ru.vlad.NauJava.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vlad.NauJava.entity.Session;
import ru.vlad.NauJava.entity.User;
import ru.vlad.NauJava.repository.TicketRepository;
import ru.vlad.NauJava.repository.SessionRepository;
import ru.vlad.NauJava.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void testBookTicket_Success() {
        // создаем тестовые данные
        Session session = new Session();
        User user = new User();
        user.setId(5L);

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        when(userRepository.findById(5L)).thenReturn(Optional.of(user));

        lenient().when(ticketRepository.existsBySessionAndRowNumberAndSeatNumber(any(), anyInt(), anyInt()))
                .thenReturn(false);

        boolean result = bookingService.bookTicket(1L, 5L, 10, 20);

        assertTrue(result);
        verify(ticketRepository).save(any());
    }
}