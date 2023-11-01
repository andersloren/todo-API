package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.User;

import java.util.HashSet;
import java.util.Set;


public class UserConverterImpl implements UserConverter{

    @Override
    public UserDTOView toUserDTOView(User entity) {



        Set<RoleDTOView> roleDTOViewList = new HashSet<>();
        for (Role role : entity.getRoles()) {
            RoleDTOView roleDTOView = new RoleDTOView();
            roleDTOView.setId(role.getId());
            roleDTOView.setName(role.getName());
            roleDTOViewList.add(roleDTOView);
        }
        return new UserDTOView(entity.getEmail(), roleDTOViewList);
    }
}
