package org.learn.board.domain.post.domain.repository;

import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByGallery(Gallery gallery, Pageable pageable);

    // 제목으로 검색
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

    // 내용으로 검색
    Page<Post> findByContentContaining(String keyword, Pageable pageable);

    // 작성자로 검색
    Page<Post> findByWriter(String keyword, Pageable pageable);

    // 제목+내용으로 검색
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    Page<Post> findByTitleContainingOrContentContaining(@Param("keyword")String keyword, Pageable pageable);
}
