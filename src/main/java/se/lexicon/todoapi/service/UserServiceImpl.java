package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g46emailsenderapi.converter.EmailConverter;
import se.lexicon.g46emailsenderapi.domain.dto.EmailDTOForm;
import se.lexicon.g46emailsenderapi.repository.EmailRepository;
import se.lexicon.g46emailsenderapi.service.EmailService;
import se.lexicon.todoapi.converter.UserConverter;
import se.lexicon.todoapi.domain.dto.UserDTOForm;
import se.lexicon.todoapi.domain.dto.UserDTOView;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.User;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ComponentScan
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final EmailService emailService;
    private final EmailRepository emailRepository;
    private final EmailConverter emailConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserConverter userConverter, EmailService emailService, EmailRepository emailRepository, EmailConverter emailConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
        this.emailService = emailService;
        this.emailRepository = emailRepository;
        this.emailConverter = emailConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTOView register(UserDTOForm userDTOForm) { // test.test@.se , 123456 , [ADMIN,USER])
        if (userDTOForm == null) throw new IllegalArgumentException("User form is null.");

        boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if (isExistEmail) throw new IllegalArgumentException("Email is already exist");

        Set<Role> roleList = userDTOForm.getRoles()
                .stream()
                .map(
                        roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                                .orElseThrow(() -> new DataNotFoundException("Role is not valid.")))
                .collect(Collectors.toSet());

        User user = new User(userDTOForm.getEmail(), passwordEncoder.encode(userDTOForm.getPassword()));
        user.setRoles(roleList);

        EmailDTOForm sentEmail = new EmailDTOForm(userDTOForm.getEmail(),
                "Registration Confirmed",
                "You've successfully registered you new user",
                1);

        emailRepository.save(emailConverter.fromFormToEntity(sentEmail));

        emailService.sendEmail(sentEmail);

        User savedUser = userRepository.save(user);

        return userConverter.toUserDTOView(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTOView getByEmail(String email) {
        User foundUser = userRepository.findById(email).orElseThrow(() -> new DataNotFoundException("Email does not exist."));

        return userConverter.toUserDTOView(foundUser);
    }

    @Override
    @Modifying
    public void disableByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateExpiredByEmail(email, true);
    }

    @Override
    @Modifying
    public void enableByEmail(String email) {
        isEmailTaken(email);
        userRepository.updateExpiredByEmail(email, false);
    }

    public void isEmailTaken(String email) {
        if (!userRepository.existsByEmail(email)) throw new DataNotFoundException("Email does not exist");
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTOView> getAll() {
        List<User> allUsers = userRepository.getAll();
        return allUsers.stream()
                .map(userConverter::toUserDTOView)
                .toList();
    }
}
