package trello.service;

import org.springframework.web.bind.annotation.PathVariable;
import trello.model.Comment;

import java.util.List;

public interface CommentService {
    Long addComment(Long taskId, Long userId, Comment commentObj);

    List<Comment> getCommentByUserId(Long commentId);

    List<Comment> getCommentByTaskId(Long commentId);
}
