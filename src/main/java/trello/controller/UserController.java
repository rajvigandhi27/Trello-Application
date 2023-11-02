package trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.model.User;
import trello.service.UserService;

import java.util.List;

@RestController
@RequestMapping("trelloApplication/users")
public class UserController {

    @Autowired
    UserService userService;
    //add user
    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }
    //delete user
    @DeleteMapping("delete")
    public void deleteUser(Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping("user/{userId}")
    public User getUserById( @PathVariable Long userId){
        return userService.getUserById(userId);
    }
}
