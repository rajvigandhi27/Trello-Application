package trello.controller;

import org.springframework.web.bind.annotation.*;
import trello.model.Comment;
import trello.service.CommentService;

@RestController
@RequestMapping("TrelloApplication/comments")
public class CommentController {

    CommentService commentService;

    @PostMapping("add")
    public Long addComment(@RequestBody Comment commentObj){
        return commentService.addComment(commentObj);
    }

    @GetMapping("get/user/{userId}")
    public Comment getCommentByUserId(@PathVariable Long userId){
        return commentService.getCommentByUserId(userId);
    }

    @GetMapping("get/Task/{taskId}")
    public Comment getCommentByTaskId(@PathVariable Long taskId){
        return commentService.getCommentByTaskId(taskId);
    }
}
