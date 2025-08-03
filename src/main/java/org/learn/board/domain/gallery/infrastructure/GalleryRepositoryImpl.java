package org.learn.board.domain.gallery.infrastructure;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.gallery.domain.GalleryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GalleryRepositoryImpl implements GalleryRepository {

    private final GalleryMapper galleryMapper;

    @Override
    public Gallery save(Gallery gallery) {
        galleryMapper.save(gallery);
        return gallery;
    }

    @Override
    public Optional<Gallery> findById(Long id) {
        return galleryMapper.findById(id);
    }

    @Override
    public Optional<Gallery> findByName(String name) {
        return galleryMapper.findByName(name);
    }

    @Override
    public List<Gallery> findAll() {
        return galleryMapper.findAll();
    }

    @Override
    public void update(Gallery gallery) {
        galleryMapper.update(gallery);
    }

    @Override
    public void deleteById(Long id) {
        galleryMapper.deleteById(id);
    }
}