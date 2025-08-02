package org.learn.board.domain.post.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PostListResponse {
    private Long id;
    private String title;
    private String writer;
    private int viewCount;
    private int likeCount;
    private LocalDateTime createdAt;
    // 참고: content와 같은 무거운 데이터는 목록 조회 시 제외하여 성능을 최적화합니다.
}
