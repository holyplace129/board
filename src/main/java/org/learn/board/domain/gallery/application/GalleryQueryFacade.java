package org.learn.board.domain.gallery.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.application.dto.GalleryResponse;
import org.learn.board.domain.gallery.application.mapper.GalleryMapper;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.gallery.domain.repository.GalleryRepository;
import org.learn.board.global.error.ErrorCode;
import org.learn.board.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GalleryQueryFacade {

    private final GalleryRepository galleryRepository;
    private final GalleryMapper galleryMapper;

    // 갤러리 목록 조회
    public List<GalleryResponse> findAllGalleries() {
        return galleryRepository.findAll().stream()
                .map(galleryMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 갤러리 상세 조회
    public GalleryResponse findGalleryByName(String name) {
        Gallery gallery = galleryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.GALLERY_NOT_FOUND));
        return galleryMapper.toResponse(gallery);
    }
}
