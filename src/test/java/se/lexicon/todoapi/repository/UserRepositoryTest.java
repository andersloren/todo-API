package se.lexicon.todoapi.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Modifying
    void testUpdateExpiredByEmail() {
        Role role = new Role("ADMIN");
        System.out.println("SAVE ROLE" + role);
        roleRepository.save(role);

        User user = new User("test@test.com", "1234");
        user.addRole(role);
        user.setExpired(false);
        System.out.println("SAVE USER");
        userRepository.save(user);



        System.out.println("SDAJLDJSALFKJASLSFJKF " + userRepository.findAll().size());
        userRepository.updateExpiredByEmail("test@test.com", true);

        userRepository.findById("test@test.com").ifPresent(element -> assertTrue(element.isExpired()));
    }


    @Test
    void testUpdatePasswordByEmail() {
    }
}


//    @Modifying
//    @Query("update User u set u.expired = :status where u.email = :email")
//    void updateExpiredByEmail(@Param("email") String email, @Param("status") boolean status);

// TODO: 02/11/2023 Fix this one! 
//    @Modifying
//    @Query("update User u set u.expired = :password where u.email = :email")
//    void updatePasswordByEmail(@Param("email") String email, @Param("password") String newPassword);