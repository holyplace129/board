package org.learn.board.Report.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReport {

    private Long id;
    private Long commentId;
    private String reporterIp;
    private String reasonCode;
    private String reasonDetail;
    private LocalDateTime createdAt;

    @Builder
    public CommentReport(Long commentId, String reporterIp, String reasonCode, String reasonDetail) {
        this.commentId = commentId;
        this.reporterIp = reporterIp;
        this.reasonCode = reasonCode;
        this.reasonDetail = reasonDetail;
    }
}