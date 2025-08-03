package org.learn.board.domain.post.domain;

import jakarta.persistence.*;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.global.domain.BaseTimeEntity;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_id", nullable = false)
    private Gallery gallery;

    @Column(length = 255, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length = 30, nullable = false)
    @ColumnDefault("'ㅇㅇ'")
    private String writer;

    @Column(nullable = false)
    private String password;

    @ColumnDefault("0")
    private int viewCount = 0;

    @ColumnDefault("0")
    private int likeCount = 0;

    @ColumnDefault("0")
    private int dislikeCount = 0;

    @ColumnDefault("0")
    private int reportCount = 0;

    @Builder
    public Post(Gallery gallery, String title, String content, String writer, String password, int viewCount, int likeCount, int dislikeCount, int reportCount) {
        this.gallery = gallery;
        this.title = title;
        this.content = content;
        this.writer = (writer != null) ? writer : "ㅇㅇ";
        this.password = password;
        this.viewCount = 0;
        this.likeCount = 0;
        this.dislikeCount = 0;
        this.reportCount = 0;
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

    public boolean isPasswordMatches(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.password);
    }
}