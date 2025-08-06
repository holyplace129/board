package org.learn.board.domain.image.controller;

import org.learn.board.global.error.ErrorCode;
import org.learn.board.global.error.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageFacade {

    @Value("${custom.upload.path}")
    private String uploadPath;

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // 1. 원본 파일명 및 확장자 추출
        String originFilename = file.getOriginalFilename();
        String fileExtension = originFilename.substring(originFilename.lastIndexOf("."));

        // 2. 고유한 파일명 생성(UUID 사용)
        String storedFilename = UUID.randomUUID().toString() + fileExtension;

        // 3. 저장할 전체 경로 생성
        String fullPath = uploadPath + storedFilename;

        // 4. 파일 저장
        file.transferTo(new File(fullPath));

        // 5. 클라이언트가 접근할 url 반환
        return "/images/" + storedFilename;
    }
}
