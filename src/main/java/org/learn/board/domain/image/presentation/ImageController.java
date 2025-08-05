package org.learn.board.domain.image.presentation;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.image.controller.ImageFacade;
import org.learn.board.domain.post.application.dto.ImageUploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageFacade imageFacade;

    @PostMapping("/images")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String uploadUrl = imageFacade.uploadImage(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ImageUploadResponse(uploadUrl));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
