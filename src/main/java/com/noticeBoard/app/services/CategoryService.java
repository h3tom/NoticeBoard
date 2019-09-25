package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.CategoryDTO;
import com.noticeBoard.app.model.Category;
import com.noticeBoard.app.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setTitle(category.getTitle());
            return categoryDTO;
        }).collect(Collectors.toList());
    }
}
