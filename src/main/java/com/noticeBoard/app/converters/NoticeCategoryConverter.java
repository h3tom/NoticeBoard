package com.noticeBoard.app.converters;

import com.noticeBoard.app.dto.CategoryDTO;
import com.noticeBoard.app.model.Category;
import com.noticeBoard.app.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class NoticeCategoryConverter implements Converter<String, CategoryDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO convert(String source) {
        Category category = categoryRepository.findOne(Long.parseLong(source));
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }
}
