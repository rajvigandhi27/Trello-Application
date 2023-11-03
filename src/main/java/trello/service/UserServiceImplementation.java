package trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trello.dao.TaskDao;
import trello.dao.UserDao;
import trello.model.Task;
import trello.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserDao userDao;
    @Autowired
    TaskDao taskDao;
    @Override
    public Long addUser(User user) {
        userDao.save(user);
        return user.getUserId();
    }

    @Override
    public List<User> getAllUsers() {return userDao.findAll();
    }

    @Override
    public Boolean deleteUser(Long userId) {
        if(userDao.findByUserId(userId)!=null){
            String username = userDao.findByUserId(userId).getUsername();
            List<Task> fetchedTask = taskDao.findByAssignedTo(username);
            for (Task task : fetchedTask){
                task.setAssignedTo(null);
                taskDao.save(task);
            }
            userDao.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.findByUserId(userId);
    }
}
