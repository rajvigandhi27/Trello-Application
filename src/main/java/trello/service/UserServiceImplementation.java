package trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trello.dao.UserDao;
import trello.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserDao userDao;
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
