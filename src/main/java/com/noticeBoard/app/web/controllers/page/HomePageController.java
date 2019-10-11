package com.noticeBoard.app.web.controllers.page;

import com.noticeBoard.app.services.CategoryService;
import com.noticeBoard.app.services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomePageController {

    private NoticeService noticeService;
    private CategoryService categoryService;

    @Autowired
    public HomePageController(NoticeService noticeService, CategoryService categoryService) {
        this.noticeService = noticeService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/{id}"})
    public String prepareHomePage(@PathVariable(required = false) Long id, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("allNotices",
                id == null ? noticeService.getAll() : noticeService.getAllByCategory(id));
        model.addAttribute("allCategories", categoryService.getAll());
        return "home";
    }
}
