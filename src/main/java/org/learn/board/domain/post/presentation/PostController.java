package org.learn.board.domain.post.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.learn.board.domain.post.application.PostFacade;
import org.learn.board.domain.post.application.dto.PostCreateRequest;
import org.learn.board.domain.post.application.dto.PostDetailResponse;
import org.learn.board.domain.post.application.dto.PostListResponse;
import org.learn.board.domain.post.application.dto.PostUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/galleries/{galleryName}/posts")
public class PostController {

    private final PostFacade postFacade;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<PostDetailResponse> createPost (
            @PathVariable String galleryName, @Valid @RequestBody PostCreateRequest request) {
        PostDetailResponse responsePost = postFacade.createPost(galleryName, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePost);
    }

    // 게시글 전제 조회
    @GetMapping("/posts")
    public ResponseEntity<Page<PostListResponse>> findAllPosts(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostListResponse> responses = postFacade.findAllPosts(pageable);
        return ResponseEntity.ok(responses);
    }


    // 갤러리 내 게시글 목록
    @GetMapping
    public ResponseEntity<Page<PostListResponse>> findPostByGallery(
            @PathVariable String galleryName,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostListResponse> responses = postFacade.findPostsByGallery(galleryName, pageable);
        return ResponseEntity.ok(responses);
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> findPostById(
            @PathVariable String galleryName,
            @PathVariable Long postId) {
        PostDetailResponse response = postFacade.findPostById(postId);
        return ResponseEntity.ok(response);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable String galleryName,
            @PathVariable Long postId,
            @Valid @RequestBody PostUpdateRequest request) {
        postFacade.updatePost(postId, request);
        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{postId")
    public ResponseEntity<Void> deletePost(
            @PathVariable String galleryName,
            @PathVariable Long postId,
            @RequestBody Map<String, String> passwordMap) {
        String password = passwordMap.get("password");
        if (password == null) {
            return ResponseEntity.badRequest().build();
        }
        postFacade.deletePost(postId, password);
        return ResponseEntity.noContent().build();
    }
}
