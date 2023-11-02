package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trello.model.Comment;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findByUserUserId(Long UserId);
    List<Comment> findByTaskTaskId(Long TaskId);
}
