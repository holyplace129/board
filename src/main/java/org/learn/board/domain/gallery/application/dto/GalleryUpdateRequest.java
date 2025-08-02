package org.learn.board.domain.gallery.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GalleryUpdateRequest {

    @NotBlank(message = "갤러리 표시 이름은 필수입니다.")
    @Size(max = 100, message = "갤러리 표시 이름은 100자를 초과할 수 없습니다.")
    private String displayName;

    @Size(max = 255, message = "갤러리 설명은 255자를 초과할 수 없습니다.")
    private String description;
}