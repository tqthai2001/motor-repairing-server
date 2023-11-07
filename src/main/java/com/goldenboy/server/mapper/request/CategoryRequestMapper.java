package com.goldenboy.server.mapper.request;

import com.goldenboy.server.common.RandomCodeGenerator;
import com.goldenboy.server.entity.Category;
import com.goldenboy.server.payload.crudrequest.CategoryRequest;
import com.goldenboy.server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestMapper {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category toCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        String code;
        do {
            code = RandomCodeGenerator.genCode("DM", 3, false, true);
        } while (categoryRepository.existsByCode(code));
        category.setCode(code);
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return category;
    }
}
