package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.dto.SimplePersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;

@Repository
public interface SimplePersonConverter {

    SimplePersonDTOView toSimplePersonDTOView(Person entity);
}
