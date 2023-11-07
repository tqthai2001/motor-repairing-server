package com.goldenboy.server.service.impl;

import com.goldenboy.server.entity.Category;
import com.goldenboy.server.mapper.dto.CategoryDTOMapper;
import com.goldenboy.server.repository.CategoryRepository;
import com.goldenboy.server.service.CategoryService;
import com.goldenboy.server.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryDTOMapper dtoMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    @Override
    @Transactional
    public Category updateById(Long id, Category newEntity) {
        Category oldEntity = this.findById(id);
        // this field cannot update
        newEntity.setId(oldEntity.getId());
        newEntity.setCode(oldEntity.getCode());
        return super.updateById(id, newEntity);
    }

    @Override
    public Map<String, Object> findAllPaging(int page, int size) {
        Map<String, Object> response = super.findAllPaging(page, size);
        List<Category> categories = (List<Category>) response.get("listOfItems");
        response.put("listOfItems", dtoMapper.toCategoryDTOs(categories));
        return response;
    }
}
