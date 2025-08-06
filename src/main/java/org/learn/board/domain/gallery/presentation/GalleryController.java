package org.learn.board.domain.gallery.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.application.GalleryFacade;
import org.learn.board.domain.gallery.application.GalleryQueryFacade;
import org.learn.board.domain.gallery.application.dto.GalleryCreateRequest;
import org.learn.board.domain.gallery.application.dto.GalleryResponse;
import org.learn.board.domain.gallery.application.dto.GalleryUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/galleries")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryFacade galleryFacade;
    private final GalleryQueryFacade galleryQueryFacade;

    // --- 쓰기 ---

    // 갤러리 생성
    @PostMapping
    public ResponseEntity<GalleryResponse> createGallery(@Valid @RequestBody GalleryCreateRequest request) {
        GalleryResponse response = galleryFacade.createGallery(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 갤러리 수정
    @PutMapping("/{name}")
    public ResponseEntity<Void> updateGallery(@PathVariable String name, @Valid @RequestBody GalleryUpdateRequest request) {
        galleryFacade.updateGallery(name, request);
        return ResponseEntity.ok().build();
    }

    // 갤러리 삭제
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteGallery(@PathVariable String name) {
        galleryFacade.deleteGallery(name);
        return ResponseEntity.ok().build();
    }

    // --- 읽기 ---

    // 갤러리 목록 조회
    @GetMapping
    public ResponseEntity<List<GalleryResponse>> findByAllGalleries() {
        List<GalleryResponse> response = galleryQueryFacade.findAllGalleries();
        return ResponseEntity.ok(response);
    }

    // 갤러리 상세 조회
    @GetMapping("/{name}")
    public ResponseEntity<GalleryResponse> findGalleryByName(@PathVariable String name) {
        GalleryResponse response = galleryQueryFacade.findGalleryByName(name);
        return ResponseEntity.ok(response);
    }



}