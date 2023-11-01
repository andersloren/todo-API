package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserConverterImpl implements UserConverter{

    @Override
    public UserDTOView toUserDTOView(User entity) {
        return new UserDTOView();
    }
}
