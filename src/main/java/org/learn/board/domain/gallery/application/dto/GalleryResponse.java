package org.learn.board.domain.gallery.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class GalleryResponse {
    private Long id;
    private String name;
    private String displayName;
    private String description;
    private LocalDateTime createdAt;
}