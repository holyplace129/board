package org.learn.board.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.comment.application.dto.CommentCreateRequest;
import org.learn.board.domain.comment.application.dto.CommentResponse;
import org.learn.board.domain.comment.application.dto.CommentUpdateRequest;
import org.learn.board.domain.comment.domain.Comment;
import org.learn.board.domain.comment.domain.repository.CommentRepository;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    // 댓글 생성
    @Transactional
    public void createComment(Long postId, CommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 대댓인 경우 부모 댓글 조회
        Comment parentComment = null;
        if (request.getParentId() != null) {
            parentComment = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 부모 댓글입니다."));
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        Comment comment = Comment.builder()
                .post(post)
                .parent(parentComment)
                .content(request.getContent())
                .writer(request.getWriter())
                .password(encryptedPassword)
                .build();

        commentRepository.save(comment);
    }

    // 게시글 내 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponse> findCommentByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        List<Comment> comments = commentRepository.findAllByPost(post);

        return buildCommentTree(comments);
    }

    // 댓글 수정
    @Transactional
    public void updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!passwordEncoder.matches(request.getPassword(), comment.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        comment.update(request.getContent());
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, String password) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!passwordEncoder.matches(password, comment.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        commentRepository.delete(comment);
    }

    // 댓글 계층 구조
    private List<CommentResponse> buildCommentTree(List<Comment> comments) {
        Map<Long, CommentResponse> commentResponseMap = comments.stream()
                .map(this::toResponse)
                .collect(Collectors.toMap(CommentResponse::getId, comment -> comment));

        commentResponseMap.values().forEach(commentResponse -> {
            Long parentId = commentResponse.getParentId();
            if (parentId != null) {
                CommentResponse parentResponse = commentResponseMap.get(parentId);
                if (parentResponse != null) {
                    parentResponse.getChildren().add(commentResponse);
                }
            }
        });

        return commentResponseMap.values().stream()
                .filter(commentResponse -> commentResponse.getParentId() == null)
                .collect(Collectors.toList());
    }

    private CommentResponse toResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());

        if (comment.getParent() != null) {
            response.setParentId(comment.getParent().getId());
        }

        response.setContent(comment.getContent());
        response.setWriter(comment.getWriter());
        response.setLikeCount(comment.getLikeCount());
        response.setCreatedAt(comment.getCreatedAt());
        return response;
    }
}
