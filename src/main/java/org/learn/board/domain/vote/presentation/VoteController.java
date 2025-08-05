package org.learn.board.domain.vote.presentation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.learn.board.domain.vote.application.VoteFacade;
import org.learn.board.global.util.ClientIpUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteFacade voteFacade;

    // 게시글 추천
    @PostMapping("/galleries/{galleryName}/posts/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, HttpServletRequest request) {
        String clientIp = ClientIpUtil.getClientIp(request);
        voteFacade.likePost(postId, clientIp);
        return ResponseEntity.ok().build();
    }

    // 게시글 비추천
    @PostMapping("/galleries/{galleryName}/posts/{postId}/dislike")
    public ResponseEntity<Void> dislikePost(@PathVariable Long postId, HttpServletRequest request) {
        String clientIp = ClientIpUtil.getClientIp(request);
        voteFacade.dislikePost(postId, clientIp);
        return ResponseEntity.ok().build();
    }

    // 댓글 추천
    @PostMapping("/galleries/{galleryName}/posts/{postId}/comments/{commentId}/like")
    public ResponseEntity<Void> likeComment(@PathVariable Long commentId, HttpServletRequest request) {
        String clientIp = ClientIpUtil.getClientIp(request);
        voteFacade.likeComment(commentId, clientIp);
        return ResponseEntity.ok().build();
    }
}
