package org.learn.board.domain.report.domain.repository;

import org.learn.board.domain.report.domain.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {
}
