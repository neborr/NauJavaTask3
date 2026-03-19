package ru.vlad.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vlad.NauJava.entity.CinemaHall;

@Repository
public interface CinemaHallRepository extends CrudRepository<CinemaHall, Long> {
}