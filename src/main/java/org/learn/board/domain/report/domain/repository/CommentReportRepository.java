package org.learn.board.domain.report.domain.repository;

import org.learn.board.domain.report.domain.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {
}
