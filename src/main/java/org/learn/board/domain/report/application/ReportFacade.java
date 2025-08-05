package org.learn.board.domain.report.application;

import lombok.RequiredArgsConstructor;
import org.learn.board.domain.comment.domain.Comment;
import org.learn.board.domain.comment.domain.repository.CommentRepository;
import org.learn.board.domain.post.domain.Post;
import org.learn.board.domain.post.domain.repository.PostRepository;
import org.learn.board.domain.report.application.dto.ReportCreateRequest;
import org.learn.board.domain.report.domain.CommentReport;
import org.learn.board.domain.report.domain.PostReport;
import org.learn.board.domain.report.domain.repository.CommentReportRepository;
import org.learn.board.domain.report.domain.repository.PostReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportFacade {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostReportRepository postReportRepository;
    private final CommentReportRepository commentReportRepository;

    // 게시글 신고
    @Transactional
    public void reportPost(Long postId, String reporterIp, ReportCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        postReportRepository.findByPostAndReporterIp(post, reporterIp).ifPresent(r -> {
            throw new IllegalArgumentException("이미 신고한 게시글입니다.");
        });

        PostReport postReport = PostReport.builder()
                .post(post)
                .reporterIp(reporterIp)
                .reasonCode(request.getReasonCode())
                .reasonDetail(request.getReasonDetail())
                .build();
        postReportRepository.save(postReport);

        post.increaseReportCount();
    }

    // 댓글 신고
    @Transactional
    public void reportComment(Long commentId, String reporterIp, ReportCreateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        commentReportRepository.findByCommentAndReporterIp(comment, reporterIp).ifPresent(r -> {
            throw new IllegalArgumentException("이미 신고한 댓글입니다.");
        });

        CommentReport commentReport = CommentReport.builder()
                .comment(comment)
                .reporterIp(reporterIp)
                .reasonCode(request.getReasonCode())
                .reasonDetail(request.getReasonDetail())
                .build();

        commentReportRepository.save(commentReport);

        comment.increaseReportCount();
    }
}
