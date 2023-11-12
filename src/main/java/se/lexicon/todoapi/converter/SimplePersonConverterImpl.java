package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.SimplePersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;

@Component
public class SimplePersonConverterImpl implements SimplePersonConverter {
    @Override
    public SimplePersonDTOView toSimplePersonDTOView(Person entity) {
        return SimplePersonDTOView.builder()
                .name(entity.getName())
                .build();
    }
}
