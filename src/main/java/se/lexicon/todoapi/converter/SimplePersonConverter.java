package se.lexicon.todoapi.converter;

import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.SimplePersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;

public interface SimplePersonConverter {

    SimplePersonDTOView toSimplePersonDTOView(Person entity);
}
