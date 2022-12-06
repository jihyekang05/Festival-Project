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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;


@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/categoryfestival")
    public String list(Model model, @PageableDefault(size =6,page=0, sort="contentViews", direction = Sort.Direction.DESC) Pageable pageable,String keyword, HttpSession session) {

        Member member = (Member)session.getAttribute("member");

        Page<Posts> festivals = null;

        String[] sortDirection  = String.valueOf(pageable.getSort()).split(":");
        String sort = sortDirection[0].trim();
        String direction = sortDirection[1].trim();

        if(member==null)
            return "redirect:/";
        if(keyword == null) {

            festivals = categoryService.listPaging(member.getMemberIndex(), pageable);
//            categoryPosts = categoryService.listPaging(member.getMemberIndex(), pageable);

        } else {
            festivals = categoryService.paging(member.getMemberIndex(), keyword, pageable);
        }
        model.addAttribute("posts",festivals);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageable", pageable);
        model.addAttribute("sort",sort);
        model.addAttribute("direction",direction);

        System.out.println("## model.getAttribute('posts')"+model.getAttribute("posts"));
        return "category_festival_board";
    }

    @ResponseBody

    @PostMapping("/categoryfestival/scroll")
    public Page<Posts> list(Model model, String keyword, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String direction, String sort, HttpSession session) {
        System.out.println("=========================");
        System.out.println("scroll page keyword :" + keyword);
        System.out.println("## categoryfestival/scroll in ");
        Member member = (Member)session.getAttribute("member");
        Page<Posts> categoryPosts=null;


        if(keyword.length() == 0) {

            if(direction.equals("DESC") && sort.equals("contentViews")) {
                categoryPosts =categoryService.sortView(member.getMemberIndex(),pageable);
            } else if (direction.equals("DESC") && sort.equals("festivalUploadDate")) {
                categoryPosts = categoryService.sortNew(member.getMemberIndex(),pageable);
            } else {
                categoryPosts = categoryService.sortOld(member.getMemberIndex(),pageable);
            }

        } else {
            categoryPosts = categoryService.paging(member.getMemberIndex(), keyword, pageable);
        }
        model.addAttribute("posts",categoryPosts);
        model.addAttribute("keyword", keyword);
        return categoryPosts;
    }
}
