package org.learn.board.domain.vote.domain.repository;

import org.learn.board.domain.vote.domain.CommentVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentVoteRepository extends JpaRepository<CommentVote, Long> {
}
