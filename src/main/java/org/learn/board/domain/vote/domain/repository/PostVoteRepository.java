package org.learn.board.domain.vote.domain.repository;

import org.learn.board.domain.vote.domain.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostVoteRepository extends JpaRepository<PostVote, Long> {
}
