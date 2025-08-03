package org.learn.board.Report.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReport {

    private Long id;
    private Long postId;
    private String reporterIp;
    private String reasonCode; // "SPAM", "ABUSE", "PORNOGRAPHY"
    private String reasonDetail;
    private LocalDateTime createdAt;

    @Builder
    public PostReport(Long postId, String reporterIp, String reasonCode, String reasonDetail) {
        this.postId = postId;
        this.reporterIp = reporterIp;
        this.reasonCode = reasonCode;
        this.reasonDetail = reasonDetail;
    }
}