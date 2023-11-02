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

    @PostMapping("/createTask")
    public ResponseEntity<Long> createTask(@RequestBody Task task){
        try{
            return new ResponseEntity<>(taskService.createTask(task), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId){
        try{
            return new ResponseEntity<>(taskService.getByTaskId(taskId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("alltasks")
    public ResponseEntity<List<Task>> showBoard(){
        try{
            return new ResponseEntity<>(taskService.showBoard(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("modifyTask")
    public ResponseEntity<Task> modifyTask(@RequestBody Task task){
            try{
                return new ResponseEntity<>(taskService.modifyTask(task), HttpStatus.OK);
            }catch(Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("deleteTask/{taskId}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long taskId){
        try{
            return new ResponseEntity<>(taskService.deleteTaskById(taskId), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
