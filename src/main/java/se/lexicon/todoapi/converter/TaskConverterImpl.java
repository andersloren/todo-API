package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Task;

@Component
public class TaskConverterImpl implements TaskConverter{
    @Override
    public TaskDTOView toRoleDTOView(Task entity) {
        return null;
    }

    @Override
    public Task toRoleEntity(TaskDTOView dtoView) {
        return null;
    }
}
