package trello.service;

import trello.model.Comment;

public interface CommentService {
    Long addComment(Comment commentObj);

    Comment getCommentByUserId(Long commentId);

    Comment getCommentByTaskId(Long commentId);
}
