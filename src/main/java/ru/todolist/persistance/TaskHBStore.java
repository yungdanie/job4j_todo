package ru.todolist.persistance;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.todolist.model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskHBStore {
    private final SessionFactory sf;

    public int executeDone(int id) {
        Session session = sf.openSession();
        Transaction tx = null;
        int res;
        try {
            tx = session.beginTransaction();
            res = session.createQuery("update Task set done = :done where id = :id")
                    .setParameter("done", true)
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    public int delete(int id) {
        Session session = sf.openSession();
        Transaction tx = null;
        int res;
        try {
            tx = session.beginTransaction();
            res = session.createQuery("delete from Task where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    public int update(Task task) {
        Session session = sf.openSession();
        Transaction tx = null;
        int res;
        try {
            tx = session.beginTransaction();
            res = session.createQuery("update Task set description = :description, done = :done where id = :id")
                    .setParameter("description", task.getDescription())
                    .setParameter("done", task.isDone())
                    .setParameter("id", task.getId())
                    .executeUpdate();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Transaction tx = null;
        Optional<Task> task;
        try {
            tx = session.beginTransaction();
            task = session.createQuery("from Task where id = :fId", Task.class)
                    .setParameter("fId", id).uniqueResultOptional();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return task;
    }

    public Task add(Task task) {
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            task.setCreated(LocalDateTime.now());
            session.save(task);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return task;
    }

    public List<Task> getAllTask() {
        List<Task> list;
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from Task", Task.class).list();
        } catch (Exception e) {
            if (tx != null) {
            tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    private List<Task> getByCondDone(boolean cond) {
        List<Task> list;
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("from Task where done = :fDone", Task.class)
                    .setParameter("fDone", cond)
                    .list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public List<Task> getDone() {
        return getByCondDone(true);
    }

    public List<Task> getNew() {
        return getByCondDone(false);
    }
}
