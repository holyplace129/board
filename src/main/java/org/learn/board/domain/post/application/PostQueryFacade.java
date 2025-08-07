package org.learn.board.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.gallery.domain.Gallery;
import org.learn.board.domain.gallery.domain.repository.GalleryRepository;
import org.learn.board.domain.post.application.dto.PostDetailResponse;
import org.learn.board.domain.post.application.dto.PostListResponse;
import org.learn.board.domain.post.application.mapper.PostMapper;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.learn.board.global.common.PageResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryFacade {

    private final PostRepository postRepository;
    private final GalleryRepository galleryRepository;
    private final PostMapper postMapper;

    // 게시글 전체 목록 조회
    public PageResponse<PostListResponse> findAllPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostListResponse> dtoPosts = posts.map(postMapper::toListResponse);
        return new PageResponse<>(dtoPosts);
    }

    // 갤러리 내 게시글 목록
    @Cacheable(value = "galleryPosts", key = "#galleryName + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public PageResponse<PostListResponse> findPostsByGallery(String galleryName, Pageable pageable) {
        Gallery gallery = galleryRepository.findByName(galleryName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 갤러리입니다."));
        Page<Post> posts = postRepository.findByGallery(gallery, pageable);
        Page<PostListResponse> dtoPage = posts.map(postMapper::toListResponse);

        return new PageResponse<>(dtoPage);
    }

    // 게시글 상세 조회
    public PostDetailResponse findPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return postMapper.toDetailResponse(post);
    }
}
