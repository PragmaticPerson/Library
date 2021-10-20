package edu.donstu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.donstu.service.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Override
    @Query("SELECT b FROM Book b ORDER BY b.id")
    List<Book> findAll();
    
    List<Book> findByNameLike(String name);
}
