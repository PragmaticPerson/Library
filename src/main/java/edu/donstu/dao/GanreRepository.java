package edu.donstu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.donstu.service.models.Ganre;

public interface GanreRepository extends JpaRepository<Ganre, Integer> {
    @Override
    @Query("SELECT g FROM Ganre g ORDER BY g.id")
    List<Ganre> findAll();
}
