package org.learn.board.Report.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReportRequest {

    @NotBlank(message = "신고 사유 코드는 필수입니다.")
    private String reasonCode;

    private String reasonDetail;
}
