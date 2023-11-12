package se.lexicon.todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.entity.Role;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleDTOView toRoleDTOView(Role entity) {
        return RoleDTOView.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
