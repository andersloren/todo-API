package se.lexicon.todoapi.domain.converter;

import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.entity.Role;

public interface RoleConverter {

    RoleDTOView toRoleDTOView(Role entity);

    Role toRoleEntity(RoleDTOView dtoView);
}
