package ru.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;


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
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "done")
    private boolean done;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Priority.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToMany(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "task_category",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> category;

    @Transient
    public ZonedDateTime getZonedCreated() {
        return created.atZone(ZoneId.of("UTC+0")).withZoneSameInstant(ZoneId.of(user.getTimeZone()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
