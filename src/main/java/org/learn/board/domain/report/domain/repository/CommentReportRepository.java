package org.learn.board.domain.report.domain.repository;

import org.learn.board.domain.comment.domain.Comment;
import org.learn.board.domain.report.domain.CommentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentReportRepository extends JpaRepository<CommentReport, Long> {

    Optional<CommentReport> findByCommentAndReporterIp(Comment comment, String reporterIp);
}
