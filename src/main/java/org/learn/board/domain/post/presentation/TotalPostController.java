package org.learn.board.domain.post.presentation;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.post.application.PostFacade;
import org.learn.board.domain.post.application.dto.PostListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class TotalPostController {

    private final PostFacade postFacade;

    // 전체 게시글 목록 조회
    @GetMapping
    public ResponseEntity<Page<PostListResponse>> findAllPosts(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostListResponse> responses = postFacade.findAllPosts(pageable);
        return ResponseEntity.ok(responses);
    }
}