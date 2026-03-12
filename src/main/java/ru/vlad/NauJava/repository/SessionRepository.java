package ru.vlad.NauJava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vlad.NauJava.entity.Session;
import java.util.List;

@Component
public class SessionRepository implements CrudRepository<Session, Long> {
    private final List<Session> sessionContainer;

    @Autowired
    public SessionRepository(List<Session> sessionContainer) {
        this.sessionContainer = sessionContainer;
    }

    @Override
    public void create(Session session) { sessionContainer.add(session); }

    @Override
    public Session read(Long id) {
        return sessionContainer.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public void update(Session session) {
        for (int i = 0; i < sessionContainer.size(); i++) {
            if (sessionContainer.get(i).getId().equals(session.getId())){
            sessionContainer.set(i, session);
            return;
            }
        }
    }


    @Override
    public void delete(Long id) { sessionContainer.removeIf(s -> s.getId().equals(id)); }
}
