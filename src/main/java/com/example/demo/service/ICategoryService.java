package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    List<CategoryDTO> findAll();
}
