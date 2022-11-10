package com.festivalP.demo.controller;

import com.festivalP.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/notice")
public class NoticeController {

    private final FestivalService festivalService;

    //공지사항 리스트 불러오기
    @GetMapping("/notice")
    public String list(Model model, @PathVariable("member_index") Long member_index) {

    }




}