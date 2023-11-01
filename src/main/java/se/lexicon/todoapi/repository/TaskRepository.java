package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // todo: select tasks by title
    // todo: select tasks by person id
    // todo: select tasks by status
    // todo: select tasks by date between start and end
    // todo: select tasks by deadline
    // todo: select all un-assigned tasks
    // todo: select all un-finished tasks
    // todo: add more as needed
}
