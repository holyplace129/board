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


}