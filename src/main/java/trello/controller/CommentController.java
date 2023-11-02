package trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trello.model.Comment;
import trello.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("TrelloApplication/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/add/{taskId}/{userId}")
    public ResponseEntity<Long> addComment(@PathVariable Long taskId, @PathVariable Long userId, @RequestBody Comment commentObj){
            try{
                System.out.println("here, in the controller");
                return new ResponseEntity<>(commentService.addComment(taskId, userId, commentObj), HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
        }

    @GetMapping("get/user/{userId}")
    public List<Comment> getCommentByUserId(@PathVariable Long userId){
        return commentService.getCommentByUserId(userId);
    }

    @GetMapping("get/task/{taskId}")
    public List<Comment> getCommentByTaskId(@PathVariable Long taskId){
        return commentService.getCommentByTaskId(taskId);
    }
}
