package com.noticeBoard.app.web.controllers;

import com.noticeBoard.app.services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice-info")
public class ShowNoticeInfoController {

    private NoticeService noticeService;

    @Autowired
    public ShowNoticeInfoController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public String prepareShowNoticeInfo(@PathVariable Long id, Model model) {
        model.addAttribute("noticeInfo", noticeService.getById(id));

        return "showNoticeInfo";
    }
}
