package ru.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo_task")
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_sequence", initialValue = 1, allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "task_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "done")
    private boolean done;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
