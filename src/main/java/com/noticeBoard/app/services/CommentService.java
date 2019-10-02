package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.AddCommentDTO;
import com.noticeBoard.app.dto.CommentDTO;
import com.noticeBoard.app.model.Comment;
import com.noticeBoard.app.model.Notice;
import com.noticeBoard.app.model.User;
import com.noticeBoard.app.repositories.CommentRepository;
import com.noticeBoard.app.repositories.NoticeRepository;
import com.noticeBoard.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private UserRepository userRepository;
    private NoticeRepository noticeRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(UserRepository userRepository, NoticeRepository noticeRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.noticeRepository = noticeRepository;
        this.commentRepository = commentRepository;
    }

    public void saveComment(AddCommentDTO commentDTO, Principal principal) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        Notice notice = noticeRepository.findOne(commentDTO.getNoticeId());
        comment.setNotice(notice);

        if (principal == null || principal.getName() == null || principal.getName().isEmpty()) {
            comment.setUnregisteredUsername("Random User");
        } else {
            User user = userRepository.getByUsername(principal.getName());
            comment.setUser(user);
        }
        commentRepository.save(comment);
    }

    public List<CommentDTO> getAllForNoticeId(Long id) {
        Notice notice = noticeRepository.findOne(id);
        List<Comment> comments = commentRepository.findAllByNoticeOrderByCreatedDesc(notice);

        return comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setContent(comment.getContent());
            commentDTO.setCreated(comment.getCreated());
            commentDTO.setUnregisteredUsername(comment.getUnregisteredUsername());
            if (comment.getUser() != null) {
                commentDTO.setRegisteredUsername(comment.getUser().getUsername());
            }
            return commentDTO;
        }).collect(Collectors.toList());
    }
}
