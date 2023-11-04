package trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.model.Comment;
import trello.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("TrelloApplication/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    // Handles the POST request to add a comment to a task by a specific user.
    @PostMapping("/addComment/{taskId}/{userId}")
    public ResponseEntity<Long> addComment(@PathVariable Long taskId, @PathVariable Long userId, @RequestBody Comment commentObj){
            try{
                return new ResponseEntity<>(commentService.addComment(taskId, userId, commentObj), HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
        }
    // Retrieves comments made by a specific user.
    @GetMapping("get/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentByUserId(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(commentService.getCommentByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    // Retrieves comments associated with a specific task.
    @GetMapping("get/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentByTaskId(@PathVariable Long taskId){
        try{
            return new ResponseEntity<>(commentService.getCommentByTaskId(taskId), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
