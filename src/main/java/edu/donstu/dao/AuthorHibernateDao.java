package edu.donstu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.donstu.service.models.Author;

@Repository
@Transactional
public class AuthorHibernateDao {
    private SessionFactory sessionFactory;

    @Autowired
    AuthorHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Author> findAll() {
        List<Author> authors;
        try (Session session = sessionFactory.openSession()) {
            authors = session.createQuery("SELECT a FROM Author a ORDER BY a.id", Author.class).list();
        }
        return authors;
    }

    public Author getOne(int id) {
        return sessionFactory.getCurrentSession().find(Author.class, id);
    }

    public void add(Author author) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(author);
            session.getTransaction().commit();
        }
    }

    public void update(Author author) {
        sessionFactory.getCurrentSession().update(author);
    }

    public void delete(Author author) {
        sessionFactory.getCurrentSession().delete(author);
    }
}
