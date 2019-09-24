package com.noticeBoard.app.web.controllers;

import com.noticeBoard.app.dto.AddNoticeDTO;
import com.noticeBoard.app.dto.CategoryDTO;
import com.noticeBoard.app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/add-notice")
public class AddNoticeController {

    private CategoryService categoryService;

    @Autowired
    public AddNoticeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("allCategories")
    public List<CategoryDTO> categories() {
        return categoryService.getAll();
    }

    @GetMapping
    public String prepareAddNotice(Model model) {
        model.addAttribute("addNotice", new AddNoticeDTO());
        return "addNotice";
    }

    @PostMapping
    public String processAddNotice(@ModelAttribute("addNotice") @Valid AddNoticeDTO noticeDTO,
                                   BindingResult result,
                                   Principal principal) {
        if (result.hasErrors()) {
            return "addNotice";
        }

        return "redirect:/";
    }
}
