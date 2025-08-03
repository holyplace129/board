package org.learn.board.domain.gallery.domain;

import java.util.List;
import java.util.Optional;

public interface GalleryRepository {

    Gallery save(Gallery gallery);

    Optional<Gallery> findById(Long id);

    Optional<Gallery> findByName(String name);

    List<Gallery> findAll();

    void update(Gallery gallery);

    void deleteById(Long id);
}