package com.noticeBoard.app.repositories;

import com.noticeBoard.app.model.Comment;
import com.noticeBoard.app.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByNoticeOrderByCreatedDesc(Notice notice);
}
