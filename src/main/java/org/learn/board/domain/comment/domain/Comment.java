package org.learn.board.domain.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    private Long id;
    private Long postId;
    private Long parentId;
    private String content;
    private String writer;
    private String password;
    private int likeCount;
    private int reportCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Comment(Long postId, Long parentId, String content, String writer, String password) {
        this.postId = postId;
        this.parentId = parentId;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.likeCount = 0;
        this.reportCount = 0;
    }

    public void update(String content) {
        this.content = content;
    }
}