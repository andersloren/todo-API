package se.lexicon.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.lexicon.todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // todo: select tasks by title
    List<Task> findByTitleContainsIgnoreCase(String title);

//    @Query("select t from Task t where t.title like :title")
//    List<Task> findTasksByTitle(@Param("title") String title);

    // todo: select tasks by person id
    List<Task> findByPerson_Id(Long person_id);

//    @Query("select t from Task t where t.person.id = :person_id")
//    List<Task> findTasksByPersonId(@Param("person_id") Long personId);

    // todo: select tasks by status
    List<Task> findByDone(boolean done);

//    @Query("select t from Task t where t.done = :status")
//    List<Task> findTasksByStatus(@Param("status") boolean done);

    // todo: select tasks by deadline between start and end
    List<Task> findByDeadlineBetween(LocalDate from, LocalDate to);

//    @Query("select t from Task t where t.deadline between :from and :to")
//    List<Task> findTasksByDateBetweenStartAndEnd(@Param("start") LocalDate startDate, @Param("to") LocalDate endDate);

    // todo: select tasks by deadline
    List<Task> findByDeadline(LocalDate deadline);

//    @Query("select t from Task t where t.deadline = :deadline")
//    List<Task> findTasksByDeadline(@Param("deadline") LocalDate deadline);

    // todo: select all un-assigned tasks
    List<Task> findByPersonNull();

//    @Query("select t from Task t where t.person = null")
//    List<Task> findTasksByUnassigned();

    // todo: select all un-finished tasks
    List<Task> findByDoneNull();

    @Query("select t from Task t where t.done = false and current_date > t.deadline")
    List<Task> findTasksByNotDoneAndNotOverdue();

    // todo: add more as needed
}
