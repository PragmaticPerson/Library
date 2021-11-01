package edu.donstu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.donstu.service.models.Comment;

@Repository
@Transactional
public class CommentHibernateDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CommentHibernateDao(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    public List<Comment> findAllForBook(int id) {
        String query = "SELECT c FROM Comment c WHERE c.book.id = " + id;

        List<Comment> comments;
        try (Session session = sessionFactory.openSession()) {
            comments = session.createQuery(query, Comment.class).list();
        }
        return comments;
    }

    public Comment getOne(int id) {
        return sessionFactory.getCurrentSession().load(Comment.class, id);
    }

    public void add(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(comment);
            session.getTransaction().commit();
        }
    }

    public void update(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }

    public void delete(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
    }

}
