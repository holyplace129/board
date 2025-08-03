package org.learn.board.domain.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostVote {
    private Long id;
    private Long postId;
    private String voterIp;
    private VoteType voteType;
    private LocalDateTime createdAt;

    public enum VoteType {
        LIKE, DISLIKE
    }

    @Builder
    public PostVote(Long postId, String voterIp, VoteType voteType) {
        this.postId = postId;
        this.voterIp = voterIp;
        this.voteType = voteType;
    }
}