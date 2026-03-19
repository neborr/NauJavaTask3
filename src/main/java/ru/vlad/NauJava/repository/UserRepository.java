package ru.vlad.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vlad.NauJava.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}