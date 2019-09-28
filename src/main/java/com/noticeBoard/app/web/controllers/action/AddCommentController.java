package com.noticeBoard.app.web.controllers.action;

import com.noticeBoard.app.dto.AddCommentDTO;
import com.noticeBoard.app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/add-comment")
public class AddCommentController {

    private CommentService commentService;

    @Autowired
    public AddCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String processAddComment(@ModelAttribute("commentData") AddCommentDTO commentDTO,
                                    Principal principal) {
        commentService.saveComment(commentDTO, principal);
        return "redirect:/notice-info/" + commentDTO.getNoticeId();
    }
}
