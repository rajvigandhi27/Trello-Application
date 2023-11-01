package trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trello.dao.CommentDao;
import trello.dao.TaskDao;
import trello.model.Comment;
import trello.model.Task;

@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    CommentDao commentDao;
    @Autowired
    TaskDao taskDao;

    @Override
    public Long addComment(Comment commentObj) {
        Long taskId = commentObj.getTask().getTaskId();
        commentObj.setTask(taskDao.findByTaskId(taskId));
        commentDao.save(commentObj);
        return commentObj.getCommentId();
    }

    @Override
    public Comment getCommentByUserId(Long commentId) {
        return commentDao.findByUserUserId(commentId);
    }

    @Override
    public Comment getCommentByTaskId(Long commentId) {
        return commentDao.findByTaskTaskId(commentId);
    }
}
