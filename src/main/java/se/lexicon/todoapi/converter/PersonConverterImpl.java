package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;

@Component
public class PersonConverterImpl implements PersonConverter{

    @Override
    public PersonDTOView toPersonDTOView(Person entity) {
        return null;
    }

    @Override
    public Person toPersonEntity(PersonDTOView dtoView) {
        return null;
    }
}
