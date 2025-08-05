package org.learn.board.domain.report.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportCreateRequest {

    @NotBlank(message = "신고 사유는 필수입니다.")
    private String reasonCode;

    private String reasonDetail;
}
