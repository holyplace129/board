package org.learn.board.domain.report.domain.repository;

import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.report.domain.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostReportRepository extends JpaRepository<PostReport, Long> {

    Optional<PostReport> findByPostAndReporterIp(Post post, String reporterIp);
}
