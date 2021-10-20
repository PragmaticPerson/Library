package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.AuthorRepository;
import edu.donstu.service.models.Author;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author getOne(int id) {
        return authorRepository.getById(id);
    }

    public void add(Author author) {
        authorRepository.saveAndFlush(author);
    }

    public void update(Author author) {
        authorRepository.saveAndFlush(author);
    }

    public void delete(int id) {
        authorRepository.deleteById(id);
    }
}
