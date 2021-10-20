package edu.donstu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.donstu.service.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.book.id = :id")
    List<Comment> findByBookId(@Param("id") int id);
}
