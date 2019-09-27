package com.noticeBoard.app.web.controllers.page;

import com.noticeBoard.app.dto.AddCommentDTO;
import com.noticeBoard.app.services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice-info")
public class ShowNoticeInfoPageController {

    private NoticeService noticeService;

    @Autowired
    public ShowNoticeInfoPageController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public String prepareShowNoticeInfoPage(@PathVariable Long id, Model model) {
        model.addAttribute("noticeInfo", noticeService.getById(id));
        model.addAttribute("commentData", new AddCommentDTO());
        return "showNoticeInfo";
    }
}
