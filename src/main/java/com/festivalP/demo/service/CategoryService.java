package com.festivalP.demo.service;

import com.festivalP.demo.domain.Category;
import com.festivalP.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findCategory(){
        return categoryRepository.findAll();
    }
}
