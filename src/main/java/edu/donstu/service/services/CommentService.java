package edu.donstu.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.donstu.dao.CommentHibernateDao;
import edu.donstu.service.models.Comment;

@Service
public class CommentService {
    private CommentHibernateDao commentRepository;

    @Autowired
    public CommentService(CommentHibernateDao commentRepository) {
        super();
        this.commentRepository = commentRepository;
    }

    public Comment get(int id) {
        return commentRepository.getOne(id);
    }

    public List<Comment> findByBookId(int id) {
        return commentRepository.findAllForBook(id);
    }

    public void delete(int id) {
        commentRepository.delete(get(id));
    }

    public void save(Comment comment) {
        commentRepository.add(comment);
    }
}
