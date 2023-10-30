package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trello.model.Task;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {
    Task findByTaskId(Long taskId);
}
