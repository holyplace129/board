package org.learn.board.domain.comment.domain.repository;

import org.learn.board.domain.comment.domain.Comment;
import org.learn.board.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
}
