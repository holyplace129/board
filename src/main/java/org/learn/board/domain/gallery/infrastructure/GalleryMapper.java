package org.learn.board.domain.gallery.infrastructure;

import org.apache.ibatis.annotations.Mapper;
import org.learn.board.domain.gallery.domain.Gallery;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GalleryMapper {

    void save(Gallery gallery);

    void update(Gallery gallery);

    void deleteById(Long id);

    Optional<Gallery> findById(Long id);

    Optional<Gallery> findByName(String name);

    List<Gallery> findAll();
}