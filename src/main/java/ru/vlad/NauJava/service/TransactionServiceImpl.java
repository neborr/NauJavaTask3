package ru.vlad.NauJava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlad.NauJava.entity.*;
import ru.vlad.NauJava.repository.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final SessionRepository sessionRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(SessionRepository sessionRepository,
                                  TicketRepository ticketRepository,
                                  UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void executeBookingTransaction(Long sessionId, Long userId, int row, int seat) {
        //Проверяем существование сеанса
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Сеанс не найден!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден!"));

        Ticket ticket = new Ticket();
        ticket.setSession(session);
        ticket.setUser(user);
        ticket.setRowNumber(row);
        ticket.setSeatNumber(seat);

        ticketRepository.save(ticket);

        System.out.println("Транзакция успешно завершена: Билет забронирован.");
    }
}