package trello.service;

import org.springframework.http.ResponseEntity;
import trello.model.Task;

import java.util.List;

public interface TaskService {
    Long createTask(Task task);
    Task modifyTask(Task task);
    ResponseEntity<Task> getByTaskId(Long TaskId);
    List<Task> getHistory(Long taskId);
    ResponseEntity<List<Task>> showBoard();
    Boolean deleteTaskById(Long taskId);
}