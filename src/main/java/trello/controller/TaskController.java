package trello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.model.Task;
import trello.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("trelloApplicatipn")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/createTask")
    Long createTask(Task task){
        return taskService.createTask(task);
    }

    @GetMapping("/task/{taskId}")
    Task getTaskById(@PathVariable Long taskId){
        return taskService.getByTaskId(taskId);
    }

    @GetMapping("alltasks")
    List<Task> showBoard(){
        System.out.println("in this method");
        return taskService.showBoard();
    }

    @PostMapping("modifyTask")
    Task modifyTask(Task task){
        return taskService.modifyTask(task);
    }

    @DeleteMapping("deleteTask")
    ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        return new ResponseEntity<>("Task Deleted", HttpStatus.OK);
    }

    @GetMapping("display")
    String display(Long taskId){
        return "this is the display";
    }
}
