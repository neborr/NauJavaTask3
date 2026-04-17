package ru.vlad.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlad.NauJava.entity.*;
import ru.vlad.NauJava.repository.*;

@Service
public class BookingServiceImpl implements BookingService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final CinemaHallRepository hallRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(SessionRepository sessionRepository,
                              MovieRepository movieRepository,
                              CinemaHallRepository hallRepository,
                              TicketRepository ticketRepository,
                              UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.hallRepository = hallRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addSession(Long movieId, Long hallId, double price) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        CinemaHall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new RuntimeException("Hall not found"));

        Session session = new Session();
        session.setMovie(movie);
        session.setHall(hall);
        session.setPrice(price);

        sessionRepository.save(session);
    }

    @Override
    public Session getSession(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean bookTicket(Long sessionId, Long userId, int row, int seat) {
        Session session = sessionRepository.findById(sessionId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (session != null && user != null) {
            Ticket ticket = new Ticket();
            ticket.setSession(session);
            ticket.setUser(user);
            ticket.setRowNumber(row);
            ticket.setSeatNumber(seat);

            ticketRepository.save(ticket);
            return true;
        }
        return false;
    }

    @Override
    public void removeSession(Long id) {
        sessionRepository.deleteById(id);
    }
}