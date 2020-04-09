package com.example.demo.service.impl;

import com.example.demo.converter.CategoryConverter;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for(CategoryEntity entity: entities) {
            CategoryDTO dto = categoryConverter.toDTO(entity);
            results.add(dto);
        }
        return results;
    }
}
