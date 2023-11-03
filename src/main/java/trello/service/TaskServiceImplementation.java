package trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trello.dao.TaskDao;
import trello.model.State;
import trello.model.Task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public Long createTask(Task task) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        task.setTaskCreated(timestamp);
        task.setState(State.TODO);
        task.setAssignedTo(null);
        taskDao.save(task);
        return task.getTaskId();
    }

    @Override
    public Task modifyTask(Task task) {
        Long fetchedTaskId = task.getTaskId();
        Task fetchedTask = taskDao.findByTaskId(fetchedTaskId);
        if (task.getDescription() != null)
            fetchedTask.setDescription(task.getDescription());
        if (task.getAssignedTo() != null)
            fetchedTask.setAssignedTo(task.getAssignedTo());
        State fetchedSate = task.getState();
        if (fetchedSate != null) {
            if (fetchedSate != State.TODO && fetchedSate != State.DOING && fetchedSate != State.DONE)
                throw new IllegalArgumentException("State value is incorrect");
            fetchedTask.setState(fetchedSate);
        }
        if (fetchedTask.getState() == State.TODO && task.getState() == State.DOING)
            fetchedTask.setTaskStarted(new Timestamp(System.currentTimeMillis()));
        if (fetchedTask.getState() == State.DONE)
            fetchedTask.setTaskCompleted(new Timestamp(System.currentTimeMillis()));
        taskDao.save(fetchedTask);
        return fetchedTask;
    }

    @Override
    public Task getByTaskId(Long taskId) {
        return taskDao.findByTaskId(taskId);
    }

    @Override
    public List<Task> getHistory(Long taskId) {
        return null;
    }

    @Override
    public List<Task> showBoard() {
        return taskDao.findAll();
    }

    @Override
    public Boolean deleteTaskById(Long taskId) {
        if (taskDao.findByTaskId(taskId) != null) {
            taskDao.deleteById(taskId);
            return true;
        }
        return false;
    }

    @Override
    public List<Task> getTaskByUsername(String username) {
        return taskDao.findByAssignedTo(username);
    }

    @Override
    public Double calculateCompletionTime() {
        List<Task> doneTasks = taskDao.findByState(State.DONE);
        if (doneTasks.isEmpty()) {
            return 0.0; // when there are no completed tasks.
        }

        double totalCompletionTime = doneTasks.stream()
                .mapToDouble(task -> {
                    Timestamp startedTime = task.getTaskStarted();
                    Timestamp completedTime = task.getTaskCompleted();

                    // ensuring timestamps are not null before calculating the difference
                    if (startedTime != null && completedTime != null) {
                        return (completedTime.getTime() - startedTime.getTime()) / 3600000.0; // Convert milliseconds to hours
                    } else {
                        return 0.0; // Handle the case when either timestamp is null.
                    }
                })
                .sum();

        return totalCompletionTime / doneTasks.size();
    }

    @Override
    public Double calculateTimeToStartDoing(Long taskId){
        Task task = taskDao.findByTaskId(taskId);

        if (task == null || task.getTaskCreated() == null || task.getTaskStarted() == null) {
            return null;
        }

        Timestamp createdTime = task.getTaskCreated();
        Timestamp startedTime = task.getTaskStarted();

        // Calculating the time it took to start doing the task in hours
        double timeToStartDoing = (startedTime.getTime() - createdTime.getTime()) / 3600000.0;

        return timeToStartDoing;
    }
}
