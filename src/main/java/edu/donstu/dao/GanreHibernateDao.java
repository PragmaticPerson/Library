package edu.donstu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.donstu.service.models.Ganre;

@Repository
@Transactional
public class GanreHibernateDao {
    private SessionFactory sessionFactory;

    @Autowired
    GanreHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Ganre> findAll() {
        List<Ganre> ganres;
        String query = "SELECT NEW edu.donstu.service.models.Ganre(g.id, g.name, COUNT(b.ganre.id)) " //
                + "FROM Ganre g " //
                + "INNER JOIN Book b ON b.ganre.id = g.id " //
                + "GROUP BY g.id, g.name " //
                + "ORDER BY g.id";
        try (Session session = sessionFactory.openSession()) {
            ganres = session.createQuery(query, Ganre.class).list();
        }
        return ganres;
    }

    public Ganre getOne(int id) {
        return sessionFactory.getCurrentSession().find(Ganre.class, id);
    }

    public void add(Ganre ganre) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(ganre);
            session.getTransaction().commit();
        }
    }

    public void update(Ganre ganre) {
        sessionFactory.getCurrentSession().update(ganre);
    }

    public void delete(Ganre ganre) {
        sessionFactory.getCurrentSession().delete(ganre);
    }

}
