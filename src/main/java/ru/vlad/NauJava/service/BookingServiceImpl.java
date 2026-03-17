package ru.vlad.NauJava.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlad.NauJava.entity.Session;
import ru.vlad.NauJava.repository.SessionRepository;

@Service
public class BookingServiceImpl implements BookingService {
    private final SessionRepository repository;

    @Autowired
    public BookingServiceImpl(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addSession(
            Long id,
            String title,
            int seats
    )
    {
        Session session = new Session();
        session.setId(id);
        session.setMovieTitle(title);
        session.setAvailableSeats(seats);
        repository.create(session);
    }

    @Override
    public Session getSession(Long id) {
        return repository.read(id);
    }

    @Override
    public boolean bookTicket(Long id) {
        Session session = repository.read(id);
        if (session != null && session.getAvailableSeats() > 0) {
            session.setAvailableSeats(session.getAvailableSeats() - 1);
            repository.update(session);
            return true;
        }
        return false;
    }

    @Override
    public void removeSession(Long id)
    {
        repository.delete(id);
    }
}
