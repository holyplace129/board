package org.learn.board.domain.vote.domain.repository;

import org.learn.board.domain.comment.domain.Comment;
import org.learn.board.domain.vote.domain.CommentVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentVoteRepository extends JpaRepository<CommentVote, Long> {

    Optional<CommentVote> findByCommentAndVoterIp(Comment comment, String voterIp);
}
