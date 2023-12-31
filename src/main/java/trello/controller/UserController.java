package trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.model.User;
import trello.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("TrelloApplication/users")
public class UserController {

    @Autowired
    UserService userService;

    // Endpoint for adding a new user to the application.
    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
    }

    // Endpoint for retrieving all users from the application.
    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Endpoint for deleting a user by their ID.
    @DeleteMapping("delete/{userId}")
    public ResponseEntity<Boolean> deleteUser( @PathVariable Long userId){
        try{
            return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    // Endpoint for retrieving a user by their ID.
    @GetMapping("user/{userId}")
    public ResponseEntity<User> getUserById( @PathVariable Long userId){

        try{
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
