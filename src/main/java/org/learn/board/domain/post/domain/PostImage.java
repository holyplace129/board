package org.learn.board.domain.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {
    private Long id;
    private Long postId;
    private String fileUrl;
    private String originalFileName;
    private Integer fileSizeKb;
    private int sortOrder;
    private LocalDateTime createdAt;

    @Builder
    public PostImage(Long postId, String fileUrl, String originalFileName, Integer fileSizeKb, int sortOrder) {
        this.postId = postId;
        this.fileUrl = fileUrl;
        this.originalFileName = originalFileName;
        this.fileSizeKb = fileSizeKb;
        this.sortOrder = sortOrder;
    }
}