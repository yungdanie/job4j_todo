package ru.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "todo_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "done")
    private boolean done;

    public Task() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id
                && done == task.done
                && Objects.equals(description, task.description)
                && Objects.equals(created, task.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done);
    }
}
