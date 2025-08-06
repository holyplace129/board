package org.learn.board.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.gallery.domain.repository.GalleryRepository;
import org.learn.board.domain.post.application.dto.PostCreateRequest;
import org.learn.board.domain.post.application.dto.PostDetailResponse;
import org.learn.board.domain.post.application.dto.PostListResponse;
import org.learn.board.domain.post.application.dto.PostUpdateRequest;
import org.learn.board.domain.post.application.mapper.PostMapper;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.PostImage;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;
    private final GalleryRepository galleryRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostMapper postMapper;

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

        return postMapper.toDetailResponse(savePost);
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

    public void increaseViewCount(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.increaseViewCount();
    }
}
