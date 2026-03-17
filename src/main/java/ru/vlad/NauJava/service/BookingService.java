package ru.vlad.NauJava.service;
import ru.vlad.NauJava.entity.Session;

public interface BookingService {
    void addSession(
            Long id,
            String title,
            int seats
    );
    Session getSession(Long id);
    boolean bookTicket(Long id);
    void removeSession(Long id);
}
