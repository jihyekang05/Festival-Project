package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.form.FestivalForm;
import com.festivalP.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final FestivalService festivalService;

    @RequestMapping("/admin")
    public String festivalManagement(Model model) {
    // 관리자 메인 페이지 (페스티벌 글 목록)
        List<Posts> festivals = festivalService.findFestivals();
        model.addAttribute("posts",festivals);
        return "festivalManagement";
    }

    @RequestMapping("/festivalWrite")
    public String festivalWrite() {
        // 축제 작성 페이지
        return "festivalWrite";
    }

    @PostMapping("/festivalWrite")
    public String fes_create( FestivalForm form, BindingResult result){
        if (result.hasErrors()) {
            return "members/festivalWrite";
        }

        Posts posts = new Posts();


        posts.setPost_num(form.getPost_num());
        posts.setContent_text(form.getContent_text());
        posts.setAdmin_index(form.getAdmin_index());
        posts.setContent_text(form.getContent_text());
        posts.setFestival_title(form.getFestival_title());
        posts.setFestival_category(form.getFestival_category());
        posts.setBoard_addr(form.getBoard_addr());
        posts.setBoard_loc_addr(form.getBoard_loc_addr());
        posts.setContent_image(form.getContent_image());
        posts.setFestival_upload_date(form.getFestival_upload_date());

        posts.setContent_views(0L);
        posts.setReview_score_avg(0L);


        festivalService.join(posts);


         return "/festivalManagement";
    }


    @RequestMapping("/noticeManagement")
    public String noticeManagement() {
    // 공지관리
        return "noticeManagement";
    }

    @RequestMapping("/noticeManagementDetail")
    public String noticeManagementDetail() {
    // 공지 관리 상세 사항
        return "noticeManagementDetail";
    }

    @RequestMapping("/memberManagement")
    public String memberManagement() {
    // 회원관리
        return "memberManagement";
    }


    @RequestMapping("/noticeWrite")
    public String noticeWrite() {
    // notice 작성 창, 수정 창 같이 사용해도 될까요?
    // 공지 작성/수정 페이지

    
        return "noticeWrite";
    }



}
