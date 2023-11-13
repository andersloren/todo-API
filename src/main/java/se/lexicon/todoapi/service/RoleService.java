package se.lexicon.todoapi.service;

import se.lexicon.todoapi.domain.dto.RoleDTOView;

import java.util.List;

public interface RoleService {

    List<RoleDTOView> getAll();
}
