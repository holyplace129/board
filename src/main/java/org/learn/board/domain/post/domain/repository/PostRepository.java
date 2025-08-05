package org.learn.board.domain.post.domain.repository;

import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByGallery(Gallery gallery, Pageable pageable);
}
