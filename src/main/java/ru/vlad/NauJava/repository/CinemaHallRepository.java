package ru.vlad.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.vlad.NauJava.entity.CinemaHall;

@Repository
@RepositoryRestResource(path = "halls")
public interface CinemaHallRepository extends CrudRepository<CinemaHall, Long> {
}