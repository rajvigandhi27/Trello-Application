package trello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.model.Task;
import trello.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("TrelloApplication/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    // Endpoint for creating a new task. It accepts a Task object in the request body.
    @PostMapping("/createTask")
    public ResponseEntity<Long> createTask(@RequestBody Task task){
        try{
            return new ResponseEntity<>(taskService.createTask(task), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
    }

    // Endpoint to retrieve a task by its ID.
    @GetMapping("/task/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId){
        try{
            return new ResponseEntity<>(taskService.getByTaskId(taskId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // Endpoint for retrieving all tasks present on the board.
    @GetMapping("alltasks")
    public ResponseEntity<List<Task>> showBoard(){
        try{
            return new ResponseEntity<>(taskService.showBoard(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Endpoint for updating an existing task.
    @PutMapping("modifyTask")
    public ResponseEntity<Task> modifyTask(@RequestBody Task task){
            try{
                return new ResponseEntity<>(taskService.modifyTask(task), HttpStatus.OK);
            }catch(Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // Endpoint for deleting a task by its ID.
    @DeleteMapping("deleteTask/{taskId}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long taskId){
        try{
            return new ResponseEntity<>(taskService.deleteTaskById(taskId), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    // Endpoint to calculate the total completion time for all tasks.
    @GetMapping("/calculate/completionTime")
    public ResponseEntity<Double> calculateCompletionTime(){
        try{
            return new ResponseEntity<>(taskService.calculateCompletionTime(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
    }

    // Endpoint to calculate the time before a task should start being worked on.
    @GetMapping("/calculate/timeToStartDoing/{taskId}")
    public ResponseEntity<Double> calculateTimeToStartDoing(@PathVariable Long taskId){
        try{
            return new ResponseEntity<>(taskService.calculateTimeToStartDoing(taskId), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(0.0, HttpStatus.BAD_REQUEST);
    }
}
