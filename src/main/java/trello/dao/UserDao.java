package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trello.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUserId(Long userId);

}
