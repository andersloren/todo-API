package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.converter.TaskConverter;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.TaskRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskConverter taskConverter;
    private TaskRepository taskRepository;
    private PersonRepository personRepository;
    private UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskConverter taskConverter,
                           TaskRepository taskRepository,
                           PersonRepository personRepository,
                           UserRepository userRepository) {
        this.taskConverter = taskConverter;
        this.taskRepository = taskRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    public TaskDTOView saveTask(TaskDTOForm taskDTOForm, Long id) {
        if (taskDTOForm == null) throw new IllegalArgumentException("Task form is null.");

        Task task = new Task(taskDTOForm.getTitle(), taskDTOForm.getDescription(), taskDTOForm.getDeadline(), taskDTOForm.isDone());

        Task savedTask = taskRepository.save(task);

        taskRepository.findById(savedTask.getId());

        return taskConverter.toTaskDTOView(savedTask);
    }

    @Override
    public TaskDTOView associateTaskWithPerson(Long id, String email) {

        if (taskRepository.findById(id).isEmpty()) throw new DataNotFoundException("Task does not exist.");

        Task unassignedTask = taskRepository.findById(id).get();
        unassignedTask.setPerson(personRepository.findByUser(userRepository.getUserByEmail(email)));
        System.out.println(unassignedTask.getPerson().getName());

        Task assignedTask = taskRepository.save(unassignedTask);
        return taskConverter.toTaskDTOView(assignedTask);
    }

    @Override
    public List<TaskDTOView> getAll() {
        List<Task> allTasks = taskRepository.getAll();
        return allTasks.stream()
                .map(task -> taskConverter.toTaskDTOView(task))
                .toList();
    }
}
