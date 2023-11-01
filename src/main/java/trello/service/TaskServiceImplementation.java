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
        Task fetchedTask = taskDao.findByTaskId(task.getTaskId());
        if(task.getDescription()!=null)
            fetchedTask.setDescription(task.getDescription());
        if(task.getAssignedTo()!=null)
            fetchedTask.setAssignedTo(task.getAssignedTo());
        if(task.getState()!=null)
        {
            String userProvidedState = task.getAssignedTo();
            try {
                State newState = State.valueOf(userProvidedState);
                // Set the new state in the task
            } catch (IllegalArgumentException e) {
                // Handle invalid state value here
            }
        }
            fetchedTask.setState(task.getState());
        if(fetchedTask.getState()==State.DONE)
            fetchedTask.setTaskCompleted(new Timestamp(System.currentTimeMillis()));
        return fetchedTask;
    }

    @Override
    public ResponseEntity<Task> getByTaskId(Long taskId) {
        try{
            return new ResponseEntity<>(taskDao.findByTaskId(taskId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Task> getHistory(Long taskId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Task>> showBoard() {
        try{
            return new ResponseEntity<>(taskDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
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
