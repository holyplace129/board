package org.learn.board.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.gallery.domain.repository.GalleryRepository;
import org.learn.board.domain.post.application.dto.PostCreateRequest;
import org.learn.board.domain.post.application.dto.PostDetailResponse;
import org.learn.board.domain.post.application.dto.PostListResponse;
import org.learn.board.domain.post.application.dto.PostUpdateRequest;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.PostImage;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;
    private final GalleryRepository galleryRepository;
    private final PasswordEncoder passwordEncoder;

    // 게시글 생성
    @Transactional
    public PostDetailResponse createPost(String galleryName, PostCreateRequest request) {
        Gallery gallery = galleryRepository.findByName(galleryName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 갤러리 입니다."));

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        Post post = Post.builder()
                .gallery(gallery)
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .password(encryptedPassword)
                .build();

        if (request.getImageUrl() != null && !request.getImageUrl().isEmpty()) {
            request.getImageUrl().forEach(imageUrl -> {
                PostImage postImage = PostImage.builder()
                        .post(post)
                        .fileUrl(imageUrl)
                        .build();
                post.addImage(postImage);
            });
        }

        Post savePost = postRepository.save(post);

        return toDetailResponse(savePost);
    }

    // 게시글 전체 목록 조회
    @Transactional(readOnly = true)
    public Page<PostListResponse> findAllPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(this::toListResponse);
    }

    // 갤러리 내 게시글 목록
    @Transactional(readOnly = true)
    public Page<PostListResponse> findPostsByGallery(String galleryName, Pageable pageable) {
        Gallery gallery = galleryRepository.findByName(galleryName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 갤러리입니다."));

        Page<Post> posts = postRepository.findByGallery(gallery, pageable);

        return posts.map(this::toListResponse);
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public PostDetailResponse findPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        post.increaseViewCount();

        return toDetailResponse(post);
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        if (!post.isPasswordMatches(request.getPassword(), passwordEncoder)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        post.update(request.getTitle(), request.getContent());
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        if (!post.isPasswordMatches(password, passwordEncoder)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        postRepository.delete(post);
    }

    private PostDetailResponse toDetailResponse(Post post) {
        PostDetailResponse response = new PostDetailResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setWriter(post.getWriter());
        response.setViewCount(post.getViewCount());
        response.setLikeCount(post.getLikeCount());
        response.setDislikeCount(post.getDislikeCount());
        response.setCreatedAt(post.getCreatedAt());
        response.setUpdatedAt(post.getUpdatedAt());
        return response;
    }

    private PostListResponse toListResponse(Post post) {
        PostListResponse response = new PostListResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setWriter(post.getWriter());
        response.setViewCount(post.getViewCount());
        response.setLikeCount(post.getLikeCount());
        response.setCreatedAt(post.getCreatedAt());
        return response;
    }
}
