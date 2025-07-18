package com.example.Connexa.Connexa.repository;

import com.example.Connexa.Connexa.entities.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
