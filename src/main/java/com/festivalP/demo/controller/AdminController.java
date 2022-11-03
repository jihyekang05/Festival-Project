package com.festivalP.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping("/")
    public String festivalManagement() {
    // 관리자 메인 페이지 (페스티벌 글 목록)
        return "festivalManagement";
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

    @RequestMapping("/festivalWrite")
    public String festivalWrite() {
    // 축제 작성 페이지
        return "festivalWrite";
    }

}
