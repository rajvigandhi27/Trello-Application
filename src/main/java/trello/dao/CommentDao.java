package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trello.model.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
    Comment findByUserUserId(Long UserId);
    Comment findByTaskTaskId(Long TaskId);
}
