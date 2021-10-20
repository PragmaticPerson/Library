package edu.donstu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.donstu.service.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Override
    @Query("SELECT a FROM Author a ORDER BY a.id")
    List<Author> findAll();
}
