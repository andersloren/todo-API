package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.todoapi.converter.PersonConverter;
import se.lexicon.todoapi.domain.dto.PersonDTOForm;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final UserRepository userRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             PersonConverter personConverter,
                             UserRepository userRepository) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public PersonDTOView create(PersonDTOForm personDTOForm, String email) {
        if (personDTOForm == null) throw new IllegalArgumentException("Person form is null.");

        Person person = new Person(personDTOForm.getName());
        person.setUser(userRepository.getUserByEmail(email));
        person.setTasks(personDTOForm.getTasks());

        Person savedPerson = personRepository.save(person);

        return personConverter.toPersonDTOView(savedPerson);
    }

    @Transactional(readOnly = true)
    public List<PersonDTOView> getAll() {
        List<Person> allPersons = personRepository.findAll();
        return allPersons.stream()
                .map(personConverter::toPersonDTOView)
                .toList();
    }
}
