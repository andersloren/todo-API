package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Task;

@Component
public class TaskConverterImpl implements TaskConverter {
    private final SimplePersonConverter simplePersonConverter;

    @Autowired
    public TaskConverterImpl(SimplePersonConverter simplePersonConverter) {
        this.simplePersonConverter = simplePersonConverter;
    }

    @Override
    public TaskDTOView toTaskDTOView(Task entity) {
        if (entity.getPerson() != null) {
            return TaskDTOView.builder()
                    .title(entity.getTitle())
                    .description(entity.getDescription())
                    .deadline(entity.getDeadline())
                    .done(entity.isDone())
                    .simplePersonDTOView(simplePersonConverter.toSimplePersonDTOView(entity.getPerson()))
                    .build();
        } else {
            return TaskDTOView.builder()
                    .title(entity.getTitle())
                    .description(entity.getDescription())
                    .deadline(entity.getDeadline())
                    .done(entity.isDone())
                    .simplePersonDTOView(null)
                    .build();
        }
    }
}