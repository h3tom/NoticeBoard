package com.noticeBoard.app.web.controllers.page;

import com.noticeBoard.app.dto.AddCommentDTO;
import com.noticeBoard.app.services.CommentService;
import com.noticeBoard.app.services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/notice-info")
public class NoticePageController {

    private NoticeService noticeService;
    private CommentService commentService;

    @Autowired
    public NoticePageController(NoticeService noticeService, CommentService commentService) {
        this.noticeService = noticeService;
        this.commentService = commentService;
    }

    @GetMapping("/{noticeId}")
    public String prepareNoticePage(@PathVariable Long noticeId, Model model, Principal principal) {
        if (noticeService.checkIfEnded(noticeId)) {
            if (principal == null || principal.getName() == null || principal.getName().isEmpty()) {
                return "redirect:/home/";
            }
        }
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("noticeInfo", noticeService.getById(noticeId));
        model.addAttribute("commentData", new AddCommentDTO());
        model.addAttribute("allComments", commentService.getAllForNoticeId(noticeId));
        model.addAttribute("localDate", LocalDate.now());
        return "showNoticeInfo";
    }
}
