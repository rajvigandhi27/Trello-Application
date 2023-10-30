package trello.service;

import trello.model.Task;

import java.util.List;

public interface TaskService {
    Long createTask(Task task);
    Task modifyTask(Task task);
    Task getByTaskId(Long TaskId);
    List<Task> getHistory(Long taskId);
    List<Task> showBoard();
    Boolean deleteTaskById(Long taskId);
}