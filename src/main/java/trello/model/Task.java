package trello.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", columnDefinition = "ENUM('TODO', 'DOING', 'DONE')")
    private State state;

    private String description;
    private Timestamp taskCreated;
    private Timestamp taskCompleted;
    private String assignedTo;

}
