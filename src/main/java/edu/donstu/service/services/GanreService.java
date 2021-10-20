package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.GanreRepository;
import edu.donstu.service.models.Ganre;

@Service
public class GanreService {

    @Autowired
    private GanreRepository ganreRepository;

    public List<Ganre> findAll() {
        return ganreRepository.findAll();
    }

    public Ganre getOne(int id) {
        return ganreRepository.getById(id);
    }

    public void add(Ganre ganre) {
        ganreRepository.saveAndFlush(ganre);
    }

    public void update(Ganre ganre) {
        ganreRepository.saveAndFlush(ganre);
    }

    public void delete(int id) {
        ganreRepository.deleteById(id);
    }
}
