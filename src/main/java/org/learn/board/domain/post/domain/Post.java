package org.learn.board.domain.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private Long id;
    private Long galleryId;
    private String title;
    private String content;
    private String writer;
    private String password;
    private int viewCount;
    private int likeCount;
    private int dislikeCount;
    private int reportCount;
    private String status; // "NORMAL", "RECOMMENDED", "DELETED"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Post(Long galleryId, String title, String content, String writer, String password) {
        this.galleryId = galleryId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.viewCount = 0;
        this.likeCount = 0;
        this.dislikeCount = 0;
        this.reportCount = 0;
        this.status = "NORMAL";
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void increaseDislikeCount() {
        this.dislikeCount++;
    }

    public void increaseReportCount() {
        this.reportCount++;
    }

}