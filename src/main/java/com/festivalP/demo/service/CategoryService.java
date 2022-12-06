package com.festivalP.demo.service;

<<<<<<< HEAD
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.repository.CategoryPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryPageRepository categoryPageRepository;

    @Transactional
    public Page<Posts> paging(Long memberId,String keyword, Pageable pageable) {

        Page<Posts> Pages= categoryPageRepository.findByFestivalTitle(memberId ,keyword, pageable);
        System.out.println("@#@#@#@#@#@ CategoryService paging");

        return Pages;
    }

    @Transactional
    public  Page<Posts> paging2(String favorite, Pageable pageable) {
        System.out.println("????? member Category ???: "+favorite);

        Page<Posts> Pages= categoryPageRepository.findByFavorite(favorite, pageable);

        System.out.println("@#@#@#@#@#@ CategoryService paging22");
        return Pages;
    }

    @Transactional
    public Page<Posts> listPaging(Long memberIndex, Pageable pageable) {
        System.out.println("????? memberIndex ???: "+memberIndex);
        Page<Posts> Pages= categoryPageRepository.findByFavoriteCategory(memberIndex, pageable);
        System.out.println("@#@#@#@#@#@ CategoryService listPaging");
        return Pages;
=======
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
>>>>>>> d292b217cccd77f706e17fb88142557db9d95e9d
    }
}
