package org.learn.board.domain.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentVote {

    private Long id;
    private Long commentId;
    private String voterIp;
    private LocalDateTime createdAt;

    @Builder
    public CommentVote(Long commentId, String voterIp) {
        this.commentId = commentId;
        this.voterIp = voterIp;
    }
}