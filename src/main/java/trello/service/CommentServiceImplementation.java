package trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trello.dao.CommentDao;
import trello.dao.TaskDao;
import trello.dao.UserDao;
import trello.model.Comment;
import trello.model.Task;
import trello.model.User;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    CommentDao commentDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    UserDao userDao;

    @Override
    public Long addComment(Long taskId, Long userId, Comment commentObj) {
        Task fetchedTask = taskDao.findByTaskId(taskId);
        User fetcedUser = userDao.findByUserId(userId);
        System.out.println("*********");
        System.out.println(fetchedTask);
        if(fetchedTask!=null && fetcedUser!=null){
            commentObj.setTask(fetchedTask);
            commentObj.setUser(fetcedUser);
            commentDao.save(commentObj);
            return commentObj.getCommentId();
        }
        return null;
    }

    @Override
    public List<Comment> getCommentByUserId(Long commentId) {
        return commentDao.findByUserUserId(commentId);
    }

    @Override
    public List<Comment> getCommentByTaskId(Long commentId) {
        return commentDao.findByTaskTaskId(commentId);
    }
}
