package org.learn.board.domain.gallery.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.application.GalleryFacade;
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

}