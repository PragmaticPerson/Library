package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.BookRepository;
import edu.donstu.service.models.Book;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    
    public List<Book> findAllByName(String name) {
        return bookRepository.findByNameLike("%" + name + "%");
    }
    
    public Book getOne(int id) {
        return bookRepository.getById(id);
    }

    public void add(Book book) {
        bookRepository.saveAndFlush(book);
    }
    
    public void update(Book book) {
        bookRepository.saveAndFlush(book);
    }
    
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
}
