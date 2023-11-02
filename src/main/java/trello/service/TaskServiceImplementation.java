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
public class TaskServiceImplementation implements TaskService{

    @Autowired
    TaskDao taskDao;

    @Override
    public Long createTask(Task task) {
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       task.setTaskCreated(timestamp);
       task.setState(State.TODO);
       taskDao.save(task);
       return task.getTaskId();
    }

    @Override
    public Task modifyTask(Task task) {
        Long fetchedTaskId = task.getTaskId();
        Task fetchedTask = taskDao.findByTaskId(fetchedTaskId);
        if(task.getDescription()!=null)
            fetchedTask.setDescription(task.getDescription());
        if(task.getAssignedTo()!=null)
            fetchedTask.setAssignedTo(task.getAssignedTo());
        State fetchedSate = task.getState();
        if(fetchedSate!=null) {
            if (fetchedSate != State.TODO && fetchedSate != State.DOING && fetchedSate != State.DONE)
                throw new IllegalArgumentException("State value is incorrect");
            fetchedTask.setState(fetchedSate);
        }
        if(fetchedTask.getState()==State.DONE)
            fetchedTask.setTaskCompleted(new Timestamp(System.currentTimeMillis()));
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
        if(taskDao.findByTaskId(taskId)!=null){
            taskDao.deleteById(taskId);
            return true;
        }
        return false;
    }
}
