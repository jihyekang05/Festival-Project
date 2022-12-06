package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
//import org.hibernate.mapping.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;


@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/categoryfestival")
    public String list(Model model, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable, String keyword, HttpSession session) {

        Member member = (Member)session.getAttribute("member");
        Page<Posts> categoryPosts = null;

        if(member==null)
            return "redirect:/";
        if(keyword == null) {
            System.out.println("## categoryController ##");
            System.out.println("## categoryController ##");


            categoryPosts = categoryService.listPaging(member.getMemberIndex(), pageable);
            List<Posts> categoryPostsList= categoryPosts.getContent();
            System.out.println("## categoryPostsList.size() ::"+categoryPostsList.size());
            System.out.println("## categoryPostsList.size() ::"+categoryPostsList.size());
            for(Posts p : categoryPostsList){
                System.out.println("$$ postNum :: "+p.getPostNum());
                System.out.println("$$ festivalTitle :: "+p.getFestivalTitle());
                System.out.println("$$ festivalCategory :: "+p.getFestivalCategory());

            }

//            categoryPosts = categoryService.listPaging(member.getMemberIndex(), pageable);

        } else {
            categoryPosts = categoryService.paging(member.getMemberIndex(), keyword, pageable);
        }
        model.addAttribute("posts",categoryPosts);

        System.out.println("## model.getAttribute('posts')"+model.getAttribute("posts"));
        return "category_festival_board";
    }

    @PostMapping("/categoryfestival/scroll")
    @ResponseBody
    public Page<Posts> list(Model model, String keyword, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
        System.out.println("=========================");
        System.out.println("scroll page keyword :" + keyword);

        Member member = (Member)session.getAttribute("member");
        Page<Posts> categoryPosts=null;


        if(keyword == null) {
            System.out.println("## categoryController ##");
            System.out.println("## categoryController ##");
            categoryPosts = categoryService.listPaging(member.getMemberIndex(), pageable);
            List<Posts> categoryPostsList= categoryPosts.getContent();
            System.out.println("## categoryPostsList.size() ::"+categoryPostsList.size());
            System.out.println("## categoryPostsList.size() ::"+categoryPostsList.size());
            for(Posts p : categoryPostsList){
                System.out.println("$$ postNum :: "+p.getPostNum());
                System.out.println("$$ festivalTitle :: "+p.getFestivalTitle());
                System.out.println("$$ festivalCategory :: "+p.getFestivalCategory());
            }

        } else {
            categoryPosts = categoryService.paging(member.getMemberIndex(), keyword, pageable);
        }
        model.addAttribute("posts",categoryPosts);
        model.addAttribute("keyword", keyword);
        return categoryPosts;
    }
}
