package com.noticeBoard.app.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddNoticeDTO {

    @Size(min = 5, max = 100)
    private String title;
    @Size(min = 5, max = 500)
    private String content;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private CommonsMultipartFile photo;
    @Size(min = 1, message = "choose at least one")
    private List<CategoryDTO> categories = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CommonsMultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(CommonsMultipartFile photo) {
        this.photo = photo;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
