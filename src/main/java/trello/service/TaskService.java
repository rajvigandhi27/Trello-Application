package trello.service;

import org.springframework.http.ResponseEntity;
import trello.model.Task;

import java.util.List;

public interface TaskService {
    Long createTask(Task task);
    Task modifyTask(Task task);
    Task getByTaskId(Long TaskId);
    List<Task> showBoard();
    Boolean deleteTaskById(Long taskId);
    List<Task> getTaskByUsername(String username);
    Double calculateCompletionTime();
    Double calculateTimeToStartDoing(Long taskId);
}