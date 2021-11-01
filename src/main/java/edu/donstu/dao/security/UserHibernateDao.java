package edu.donstu.dao.security;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.donstu.service.models.security.User;

@Repository
@Transactional
public class UserHibernateDao {
    private SessionFactory sessionFactory;

    @Autowired
    public UserHibernateDao(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    public List<User> findAll() {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            users = session.createQuery("SELECT u FROM User u", User.class).list();
        }
        return users;
    }

    public User findByUsername(String username) {
        String query = "SELECT u FROM User u WHERE u.username = '" + username + "'";
        return findUserByQuery(query);
    }

    public User findByEmail(String email) {
        String query = "SELECT u FROM User u WHERE u.email = '" + email + "'";
        return findUserByQuery(query);
    }

    public User getOne(int id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    public void add(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    private User findUserByQuery(String query) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            user = session.createQuery(query, User.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }
}
