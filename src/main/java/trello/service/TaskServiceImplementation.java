package trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trello.dao.TaskDao;
import trello.model.Task;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService{

    @Autowired
    TaskDao taskDao;

    @Override
    public Long createTask(Task task) {
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       task.setTaskCreated(timestamp);
       task.setState("ToDo");
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
            fetchedTask.setState(task.getState());
        if(fetchedTask.getState().equalsIgnoreCase("done"))
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
