package trello.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String username;
    private String email;

    //@OneToMany
    //private List<Task> task;
    //userName
    //email
    //userRole: creator/admin
    //one task: multiple comments taskId as a foreign key : text, taskId, userId.
    //many users to many comments : userId as a foreign key
    //if either state is changed or description is changed.
}
