package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trello.model.State;
import trello.model.Task;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {
    Task findByTaskId(Long taskId);
    List<Task> findByAssignedTo(String username);
    List<Task> findByState(State state);
}
