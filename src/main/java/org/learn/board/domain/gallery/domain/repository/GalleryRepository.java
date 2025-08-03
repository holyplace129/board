package org.learn.board.domain.gallery.domain.repository;

import org.learn.board.domain.gallery.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Optional<Gallery> findByName(String name);
}