package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.AuthorHibernateDao;
import edu.donstu.service.models.Author;

@Service
public class AuthorService {
    private AuthorHibernateDao authorRepository;

    @Autowired
    public AuthorService(AuthorHibernateDao authorRepository) {
        super();
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author getOne(int id) {
        return authorRepository.getOne(id);
    }

    public void add(Author author) {
        authorRepository.add(author);
    }

    public void update(Author author) {
        authorRepository.update(author);
    }

    public void delete(int id) {
        authorRepository.delete(getOne(id));
    }
}
