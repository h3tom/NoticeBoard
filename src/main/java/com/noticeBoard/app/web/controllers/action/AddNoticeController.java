package com.noticeBoard.app.web.controllers.action;

import com.noticeBoard.app.dto.AddNoticeDTO;
import com.noticeBoard.app.dto.CategoryDTO;
import com.noticeBoard.app.services.CategoryService;
import com.noticeBoard.app.services.NoticeService;
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
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/add-notice")
public class AddNoticeController {

    private CategoryService categoryService;
    private NoticeService noticeService;

    @Autowired
    public AddNoticeController(CategoryService categoryService, NoticeService noticeService) {
        this.categoryService = categoryService;
        this.noticeService = noticeService;
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
        if (noticeDTO.getEndDate().isBefore(LocalDate.now())) {
            result.rejectValue("endDate",
                    null,
                    "must be at least today");
            return "addNotice";
        }
        noticeService.saveNotice(noticeDTO, principal.getName());
        return "redirect:/home";
    }
}
