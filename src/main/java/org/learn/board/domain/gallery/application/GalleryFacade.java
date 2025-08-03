package org.learn.board.domain.gallery.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.application.dto.GalleryCreateRequest;
import org.learn.board.domain.gallery.application.dto.GalleryResponse;
import org.learn.board.domain.gallery.application.dto.GalleryUpdateRequest;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.gallery.domain.GalleryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GalleryFacade {

    private final GalleryRepository galleryRepository;

    // 갤러리 생성
    @Transactional
    public GalleryResponse createGallery(GalleryCreateRequest request) {
        // 이름 중복 체크
        galleryRepository.findByName(request.getName()).ifPresent(g -> {
            throw new IllegalArgumentException("이미 존재하는 갤러리 이름입니다.");
        });

        // DTO를 도메인 객체로 변환
        Gallery gallery = Gallery.builder()
                .name(request.getName())
                .displayName(request.getDisplayName())
                .description(request.getDescription())
                .build();

        // 저장
        Gallery savedGallery = galleryRepository.save(gallery);

        // 도메인 객체를 응답 DTO로 변환하여 반환
        return toResponse(savedGallery);
    }

    // 갤러리 목록 조회
    @Transactional(readOnly = true)
    public List<GalleryResponse> findAllGalleries() {
        return galleryRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 갤러리 상세 조회
    @Transactional(readOnly = true)
    public GalleryResponse findGalleryByName(String name) {
        Gallery gallery = galleryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 갤러리입니다."));
        return toResponse(gallery);
    }

    // 갤러리 수정
    @Transactional
    public void updateGallery(String name, GalleryUpdateRequest request) {
        // 수정할 갤러리 조회
        Gallery gallery = galleryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 갤러리입니다."));

        gallery.update(request.getDisplayName(), request.getDescription());

        // 변경 내용 저장
        galleryRepository.update(gallery);
    }

    // 갤러리 삭제
    @Transactional
    public void deleteGallery(String name) {
        // 갤러리 조회
        Gallery gallery = galleryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 갤러리입니다."));

        // 삭제
        galleryRepository.deleteById(gallery.getId());
    }


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