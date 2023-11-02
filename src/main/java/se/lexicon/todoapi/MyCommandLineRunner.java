package se.lexicon.todoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Role;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.domain.entity.User;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.TaskRepository;
import se.lexicon.todoapi.repository.UserRepository;
import se.lexicon.todoapi.service.UserServiceImpl;

import java.time.LocalDate;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;


//    public MyCommandLineRunner(PersonRepository personRepository
//            , RoleRepository roleRepository
//            , TaskRepository taskRepository
//            , UserRepository userRepository
//            , UserServiceImpl userService
//    ) {
//        this.personRepository = personRepository;
//        this.roleRepository = roleRepository;
//        this.taskRepository = taskRepository;
//        this.userRepository = userRepository;
//        this.userService = userService;
//    }

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person("Person_1");
        User user = new User("email@domain.com", "1234");
        Role role = new Role("ADMIN");
        user.addRole(role);
        person.setUser(user);
        Task task1 = new Task("Task_1", "Description_1", LocalDate.now().plusDays(5), false, person);
        Task task2 = new Task("Task_2", "Description_2", LocalDate.now().plusDays(10), true, person);
        Task task3 = new Task("Task_3", "Description_3", LocalDate.now().plusDays(15), false, person);
        Task task4 = new Task("Task_4", "Description_4", LocalDate.now().plusDays(20), true, person);

        System.out.println(person);
        System.out.println(user);
        System.out.println(role);
        System.out.println(task1);
        System.out.println(task2);
        System.out.println(task3);
        System.out.println(task4);

//        userRepository.save(user);
//        roleRepository.save(role);
        personRepository.save(person);
//        taskRepository.save(task1);
//        taskRepository.save(task2);
//        taskRepository.save(task3);
//        taskRepository.save(task4);
//
//        taskRepository.findTasksByDeadline(LocalDate.now().plusDays(5)).forEach(System.out::println);
//        taskRepository.findTasksByDateBetweenStartAndEnd(LocalDate.now().plusDays(5), LocalDate.now().plusDays(20)).forEach(System.out::println);
//        taskRepository.findTasksByNotDone().forEach(System.out::println);
//        taskRepository.findTasksByStatus(true).forEach(System.out::println);
//        taskRepository.findTasksByTitle("Task_1").forEach(System.out::println);
//        taskRepository.findTasksByPersonId(1L).forEach(System.out::println);
//        taskRepository.findTasksByUnassigned().forEach(System.out::println);
    }
}
