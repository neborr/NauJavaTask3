package ru.vlad.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.vlad.NauJava.entity.Session;

@Repository
@RepositoryRestResource(path = "sessions")
public interface SessionRepository extends CrudRepository<Session, Long> {}