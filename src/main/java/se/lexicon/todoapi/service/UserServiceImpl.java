package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.converter.UserConverter;
import se.lexicon.todoapi.converter.UserConverterImpl;
import se.lexicon.todoapi.domain.dto.RoleDTOView;
import se.lexicon.todoapi.domain.dto.UserDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.User;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.exception.ExpiredException;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           UserConverter userConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
    }

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) { // test.test@.se , 123456 , [ADMIN,USER])
        // chech params
        if (userDTOForm == null) throw new IllegalArgumentException("User form is null.");

        // check if email exist
        boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if (isExistEmail) throw new IllegalArgumentException("Email is already exist");

        // retrieve and validate roles
        Set<Role> roleList = userDTOForm.getRoles()
                .stream()
                .map(
                        roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                                .orElseThrow(() -> new DataNotFoundException("Role is not valid.")))
                .collect(Collectors.toSet());

//        // hash password
//        // convert dto to entity
        User user = new User(userDTOForm.getEmail(), passwordEncoder.encode(userDTOForm.getPassword()));

//        // create a new user entity
        User savedUser = userRepository.save(user);

        return UserDTOView.builder()
                .email(savedUser.getEmail())
                .roles(savedUser.getRoles()
                                .stream()
                                .map(role -> RoleDTOView.builder()
                                        .id(role.getId())
                                        .name(role.getName())
                                        .build())
                                .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public UserDTOView getByEmail(String email) {
        User foundUser = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exist."));
        return UserDTOView.builder()
                .email(foundUser.getEmail())
                .roles(foundUser.getRoles()
                        .stream()
                        .map(role -> RoleDTOView.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void disableByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateExpiredByEmail(email, true);
    }

    @Override
    public void enableByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateExpiredByEmail(email, false);
    }

    private void isEmailTaken(String email){
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist");
    }
}
