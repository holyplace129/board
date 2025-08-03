package org.learn.board.domain.gallery.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.application.dto.GalleryCreateRequest;
import org.learn.board.domain.gallery.application.dto.GalleryResponse;
import org.learn.board.domain.gallery.application.dto.GalleryUpdateRequest;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.gallery.domain.repository.GalleryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GalleryFacade {

    private final GalleryRepository galleryRepository;

    // 갤러리 생성
    public GalleryResponse createGallery(GalleryCreateRequest request) {
        if (galleryRepository.findByName(request.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 갤러리 이름입니다.");
        }

        Gallery gallery = Gallery.builder()
                .name(request.getName())
                .displayName(request.getDisplayName())
                .description(request.getDescription())
                .build();

        Gallery savedGallery = galleryRepository.save(gallery);

        return toResponse(savedGallery);
    }

    // 갤러리 목록 조회
    public List<GalleryResponse> findAllGalleries() {
        return galleryRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 갤러리 상세 조회


    // 갤러리 수정


    // 갤러리 삭제




    private GalleryResponse toResponse(Gallery gallery) {
        GalleryResponse response = new GalleryResponse();
        response.setId(gallery.getId());
        response.setName(gallery.getName());
        response.setDisplayName(gallery.getDisplayName());
        response.setDescription(gallery.getDescription());
        response.setCreatedAt(gallery.getCreatedAt());
        return response;
    }
}