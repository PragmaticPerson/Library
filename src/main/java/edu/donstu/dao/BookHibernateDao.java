package edu.donstu.dao;

import java.util.List;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.donstu.service.models.Book;

@Repository
@Transactional
public class BookHibernateDao {
    private SessionFactory sessionFactory;

    @Autowired
    public BookHibernateDao(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    public List<Book> findAll() {
        List<Book> books;
        try (Session session = sessionFactory.openSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(Book.class);
            Root<Book> order = query.from(Book.class);

            query.select(order);
            query.orderBy(builder.asc(order.get("id")));

            books = session.createQuery(query).list();
        }
        return books;
    }

    public List<Book> findAllByName(String name) {
        List<Book> books;
        String query = "SELECT b FROM Book b WHERE lower(b.name) LIKE '%" + name.toLowerCase() + "%'";
        try (Session session = sessionFactory.openSession()) {
            books = session.createQuery(query, Book.class).list();
        }
        return books;
    }

    public Book getOne(int id) {
        Book book;
        String query = "SELECT b FROM Book b WHERE b.id = " + id;
        try (Session session = sessionFactory.openSession()) {
            book = session.createQuery(query, Book.class).getSingleResult();
        }
        return book;
    }

    public void add(Book book) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
        }
    }

    public void update(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }

    public void delete(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

}
