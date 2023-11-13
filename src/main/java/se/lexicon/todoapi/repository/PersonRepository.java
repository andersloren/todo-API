package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.User;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findPersonByTasksIsEmpty();

    Person findByUser(User user);

}
