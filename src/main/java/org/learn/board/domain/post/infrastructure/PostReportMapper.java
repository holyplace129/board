package org.learn.board.domain.post.infrastructure;

import org.apache.ibatis.annotations.Mapper;
import org.learn.board.domain.post.domain.PostReport;

import java.util.Optional;

@Mapper
public interface PostReportMapper {

    void save(PostReport postReport);

    Optional<PostReport> findByPostIdAndReporterIp(Long postId, String reporterIp);

}