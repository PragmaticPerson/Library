package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.GanreHibernateDao;
import edu.donstu.service.models.Ganre;

@Service
public class GanreService {
    private GanreHibernateDao ganreRepository;

    @Autowired
    public GanreService(GanreHibernateDao ganreRepository) {
        super();
        this.ganreRepository = ganreRepository;
    }

    public List<Ganre> findAll() {
        return ganreRepository.findAll();
    }

    public Ganre getOne(int id) {
        return ganreRepository.getOne(id);
    }

    public void add(Ganre ganre) {
        ganreRepository.add(ganre);
    }

    public void update(Ganre ganre) {
        ganreRepository.update(ganre);
    }

    public void delete(int id) {
        ganreRepository.delete(getOne(id));
    }
}
