package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.BookHibernateDao;
import edu.donstu.service.models.Book;

@Service
public class BookService {
    private BookHibernateDao bookRepository;

    @Autowired
    public BookService(BookHibernateDao bookRepository) {
        super();
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAllByName(String name) {
        return bookRepository.findAllByName(name);
    }

    public Book getOne(int id) {
        return bookRepository.getOne(id);
    }

    public void add(Book book) {
        bookRepository.add(book);
    }

    public void update(Book book) {
        bookRepository.update(book);
    }

    public void delete(int id) {
        bookRepository.delete(getOne(id));
    }
}
