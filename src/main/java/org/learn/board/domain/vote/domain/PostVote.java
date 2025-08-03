package org.learn.board.domain.vote.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.global.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uk_post_vote", columnNames = {"post_id", "voterIp"})
})
@Entity
public class PostVote extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(length = 45, nullable = false)
    private String voterIp;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private VoteType voteType;

    public enum VoteType {
        LIKE, DISLIKE
    }

    @Builder
    public PostVote(Post post, String voterIp, VoteType voteType) {
        this.post = post;
        this.voterIp = voterIp;
        this.voteType = voteType;
    }
}