package trello.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trello.model.User;

import java.util.List;

@Service
public interface UserService {
    Long addUser(User user);

    ResponseEntity<List<User>> getAllUsers();

    void deleteUser(Long userId);

    User getUserById(Long userId);
}
