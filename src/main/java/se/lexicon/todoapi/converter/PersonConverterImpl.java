package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Person;

@Component
public class PersonConverterImpl implements PersonConverter {

    private final SimplePersonConverter simplePersonConverter;

    @Autowired
    public PersonConverterImpl(SimplePersonConverter simplePersonConverter) {
        this.simplePersonConverter = simplePersonConverter;
    }

    @Override
    public PersonDTOView toPersonDTOView(Person entity) {
        return PersonDTOView.builder()
                .name(entity.getName())
                .tasks(entity.getTasks().stream()
                        .map(task -> {
                            TaskDTOView dto = new TaskDTOView();
                            dto.setTitle(task.getTitle());
                            dto.setDescription(task.getDescription());
                            dto.setDeadline(task.getDeadline());
                            dto.setDone(task.isDone());
                            dto.setSimplePersonDTOView(simplePersonConverter.toSimplePersonDTOView(task.getPerson()));
                            return dto;
                        }).toList())
                .build();
    }
}
