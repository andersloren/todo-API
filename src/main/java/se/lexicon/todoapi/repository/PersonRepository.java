package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findPersonByTasksIsEmpty();

    Person findByUser(User user);

}
