package org.learn.board.domain.comment.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CommentResponse {
    private Long id;
    private Long parentId;
    private String content;
    private String writer;
    private int likeCount;
    private LocalDateTime createdAt;
    private List<CommentResponse> children = new ArrayList<>(); // 대댓글 목록
}
