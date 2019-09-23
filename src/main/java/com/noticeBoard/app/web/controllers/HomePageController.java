package com.noticeBoard.app.web.controllers;

import com.noticeBoard.app.services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {

    private NoticeService noticeService;

    @Autowired
    public HomePageController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public String prepareHomePage(Model model) {
        model.addAttribute("allNotices", noticeService.getAll());
        return "homePage";
    }
}
