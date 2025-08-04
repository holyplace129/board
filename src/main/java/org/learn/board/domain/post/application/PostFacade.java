package org.learn.board.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.post.application.dto.PostCreateRequest;
import org.learn.board.domain.post.application.dto.PostDetailResponse;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;

    // 게시글 생성
    public PostDetailResponse createPost(PostCreateRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .password(request.getPassword())
                .build();

        Post savePost = postRepository.save(post);

        return toResponse(savePost);
    }

    // 게시글 전체 목록 조회

    // 갤러리 게시글 목록 조회

    private PostDetailResponse toResponse(Post post) {
        PostDetailResponse response = new PostDetailResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setWriter(post.getWriter());
        response.setCreatedAt(post.getCreatedAt());
        return response;
    }
}
