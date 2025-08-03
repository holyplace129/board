package org.learn.board.domain.vote.domain.repository;

import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.vote.domain.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostVoteRepository extends JpaRepository<PostVote, Long> {

    Optional<PostVote> findByPostAndVoterIp(Post post, String voterIp);
}
