package org.learn.board.domain.vote.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.comment.domain.Comment;
import org.learn.board.domain.comment.domain.repository.CommentRepository;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.learn.board.domain.vote.domain.CommentVote;
import org.learn.board.domain.vote.domain.PostVote;
import org.learn.board.domain.vote.domain.repository.CommentVoteRepository;
import org.learn.board.domain.vote.domain.repository.PostVoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class VoteFacade {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostVoteRepository postVoteRepository;
    private final CommentVoteRepository commentVoteRepository;


    // 게시글 추천
    @Transactional
    public void likePost(Long postId, String voterIp) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        postVoteRepository.findByPostAndVoterIp(post, voterIp).ifPresent(v -> {
            throw new IllegalArgumentException("이미 추천/비추천 한 게시글입니다.");
        });

        PostVote postVote = PostVote.builder()
                .post(post)
                .voterIp(voterIp)
                .voteType(PostVote.VoteType.LIKE)
                .build();
        postVoteRepository.save(postVote);

        post.increaseLikeCount();
    }

    // 게시글 비추천
    @Transactional
    public void dislikePost(Long postId, String voterIp) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        postVoteRepository.findByPostAndVoterIp(post, voterIp).ifPresent(v -> {
            throw new IllegalArgumentException("이미 추천/비추천한 게시글입니다.");
        });

        PostVote postVote = PostVote.builder()
                .post(post)
                .voterIp(voterIp)
                .voteType(PostVote.VoteType.DISLIKE)
                .build();
        postVoteRepository.save(postVote);

        post.increaseDislikeCount();
    }

    @Transactional
    public void likeComment(Long commentId, String voterIp) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        commentVoteRepository.findByCommentAndVoterIp(comment, voterIp).ifPresent(v -> {
            throw new IllegalArgumentException("이미 추천한 게시글입니다.");
        });

        CommentVote commentVote = CommentVote.builder()
                .comment(comment)
                .voterIp(voterIp)
                .build();
        commentVoteRepository.save(commentVote);

        comment.increaseLikeCount();
    }
}
