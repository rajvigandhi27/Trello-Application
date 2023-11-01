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
    public ResponseEntity<Long> addUser(User user) {
        userDao.save(user);
        return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        try{
            return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.findByUserId(userId);
    }
}
