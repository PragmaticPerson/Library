package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.CommentRepository;
import edu.donstu.service.models.Comment;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment get(int id) {
        return commentRepository.getById(id);
    }

    public List<Comment> findByBookId(int id) {
        return commentRepository.findByBookId(id);
    }

    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    public void save(Comment comment) {
        commentRepository.saveAndFlush(comment);
    }
}
