package org.learn.board.domain.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.learn.board.global.domain.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.Base64;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_comment_vote", columnNames = {"comment_id", "voteIp"})
})
@Entity
public class CommentVote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(length = 45, nullable = false)
    private String voterIp;

    @Builder
    public CommentVote(Comment comment, String voterIp) {
        this.comment = comment;
        this.voterIp = voterIp;
    }
}