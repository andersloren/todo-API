package se.lexicon.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.todoapi.converter.TaskConverter;
import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.exception.DataNotFoundException;
import se.lexicon.todoapi.repository.PersonRepository;
import se.lexicon.todoapi.repository.TaskRepository;
import se.lexicon.todoapi.repository.UserRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskConverter taskConverter;
    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

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

    @Transactional
    public TaskDTOView saveTask(TaskDTOForm taskDTOForm, Long id) {
        if (taskDTOForm == null) throw new IllegalArgumentException("Task form is null.");

        Task task = new Task(taskDTOForm.getTitle(), taskDTOForm.getDescription(), taskDTOForm.getDeadline(), taskDTOForm.isDone());

        Task savedTask = taskRepository.save(task);

        taskRepository.findById(savedTask.getId());

        return taskConverter.toTaskDTOView(savedTask);
    }

    @Override
    @Modifying
    public TaskDTOView associateTaskWithPerson(Long id, String email) {

        if (taskRepository.findById(id).isEmpty()) throw new DataNotFoundException("Task does not exist.");

        Task unassignedTask = taskRepository.findById(id).get();
        unassignedTask.setPerson(personRepository.findByUser(userRepository.getUserByEmail(email)));
        System.out.println(unassignedTask.getPerson().getName());

        Task assignedTask = taskRepository.save(unassignedTask);
        return taskConverter.toTaskDTOView(assignedTask);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTOView> getAll() {
        List<Task> allTasks = taskRepository.getAll();
        return allTasks.stream()
                .map(taskConverter::toTaskDTOView)
                .toList();
    }
}
