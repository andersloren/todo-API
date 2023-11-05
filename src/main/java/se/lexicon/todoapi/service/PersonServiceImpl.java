package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.converter.PersonConverter;
import se.lexicon.todoapi.domain.dto.PersonDTOForm;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.TaskRepository;
import se.lexicon.todoapi.repository.UserRepository;

@Service
public class PersonServiceImpl {

    private PersonRepository personRepository;
    private PersonConverter personConverter;
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             PersonConverter personConverter,
                             TaskRepository taskRepository,
                             UserRepository userRepository) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public PersonDTOView create(PersonDTOForm personDTOForm, String email) {
        if (personDTOForm == null) throw new IllegalArgumentException("Person form is null.");

        Person person = new Person(personDTOForm.getName());
        person.setUser(userRepository.getUserByEmail(email));
        person.setTasks(personDTOForm.getTasks());

        Person savedPerson = personRepository.save(person);

        return personConverter.toPersonDTOView(savedPerson);
    }
}
