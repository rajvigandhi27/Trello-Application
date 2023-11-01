package trello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.model.Task;
import trello.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("trelloApplication/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/createTask")
    Long createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId){
        return taskService.getByTaskId(taskId);
    }

    @GetMapping("alltasks")
    public ResponseEntity<List<Task>> showBoard(){
        return taskService.showBoard();
    }

    @PostMapping("modifyTask")
    Task modifyTask(Task task){

        return taskService.modifyTask(task);
    }

    @DeleteMapping("deleteTask/{taskId}")
    public Boolean deleteTask(@PathVariable Long taskId){
        return taskService.deleteTaskById(taskId);
    }
}
