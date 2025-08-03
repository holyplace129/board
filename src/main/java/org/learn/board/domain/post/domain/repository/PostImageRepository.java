package org.learn.board.domain.post.domain.repository;

import org.learn.board.domain.post.domain.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
