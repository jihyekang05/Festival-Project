package com.festivalP.demo.service;


import com.festivalP.demo.domain.Category;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.repository.CategoryPageRepository;
import com.festivalP.demo.repository.CategoryRepository;
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

    private final CategoryRepository categoryRepository;
    @Transactional
    public Page<Posts> paging(Long memberId,String keyword, Pageable pageable) {

        Page<Posts> Pages= categoryPageRepository.findByFavoriteCategory(memberId ,keyword, pageable);
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
        System.out.println("????? memberIndex ???: " + memberIndex);
        Page<Posts> Pages = categoryPageRepository.findByFavoriteCategory(memberIndex, pageable);
        System.out.println("@#@#@#@#@#@ CategoryService listPaging");
        return Pages;

    }

    public List<Category> findCategory(){
        return categoryRepository.findAll();

    }

    public Page<Posts> sortOld(Long memberIndex, Pageable pageable) {
        Page<Posts> Pages = categoryPageRepository.findAllByOrderByFestivalUploadDate(memberIndex, pageable);
        return Pages;
    }
    //최신 순
    public Page<Posts> sortNew(Long memberIndex, Pageable pageable) {
        Page<Posts> Pages = categoryPageRepository.findAllByOrderByFestivalUploadDateDesc(memberIndex, pageable);
        return Pages;
    }

    //조회수 순
    public Page<Posts> sortView(Long memberIndex, Pageable pageable) {
        Page<Posts> Pages = categoryPageRepository.findAllByOrderByContentViewsDesc(memberIndex, pageable);

        return Pages;
    }

}
