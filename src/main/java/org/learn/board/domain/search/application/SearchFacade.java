package org.learn.board.domain.search.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.post.application.dto.PostListResponse;
import org.learn.board.domain.post.application.mapper.PostMapper;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.learn.board.domain.search.application.dto.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchFacade {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Page<PostListResponse> searchPosts(SearchRequest searchRequest, Pageable pageable) {
        if (searchRequest.getKeyword() == null || searchRequest.getKeyword().isBlank()) {
            return Page.empty();
        }

        Page<Post> posts;
        switch (searchRequest.getType()) {
            case TITLE:
                posts = postRepository.findByTitleContaining(searchRequest.getKeyword(), pageable);
                break;
            case CONTENT:
                posts = postRepository.findByContentContaining(searchRequest.getKeyword(), pageable);
                break;
            case WRITER:
                posts = postRepository.findByWriter(searchRequest.getKeyword(), pageable);
                break;
            case TITLE_CONTENT:
                posts = postRepository.findByTitleContainingOrContentContaining(searchRequest.getKeyword(), pageable);
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 검색 타입입니다: " + searchRequest.getType());
        }

        return posts.map(postMapper::toListResponse);
    }

}
