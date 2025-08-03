package org.learn.board.domain.gallery.domain.repository;

import org.learn.board.domain.gallery.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Gallery save(Gallery gallery);

    Optional<Gallery> findById(Long id);

    Optional<Gallery> findByName(String name);

    List<Gallery> findAll();

    void update(Gallery gallery);

    void deleteById(Long id);
}