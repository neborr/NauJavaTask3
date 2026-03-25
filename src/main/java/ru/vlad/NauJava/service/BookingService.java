package ru.vlad.NauJava.service;

import ru.vlad.NauJava.entity.Session;

public interface BookingService {
    void addSession(Long movieId, Long hallId, double price);
    Session getSession(Long id);
    boolean bookTicket(Long sessionId, Long userId, int row, int seat); // Бронирование для пользователя
    void removeSession(Long id);
}