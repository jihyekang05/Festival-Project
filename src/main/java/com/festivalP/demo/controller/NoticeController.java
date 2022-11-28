package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Notice;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.service.FestivalService;
import com.festivalP.demo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    //공지사항 리스트 불러오기
    @GetMapping("/notice")
    public String list(Model model, @PageableDefault(size = 5, page = 0, direction = Sort.Direction.DESC) Pageable pageable, String keyword) {
        System.out.println("notice keyword:"+keyword);
        Page<Notice> notice = noticeService.paging(keyword, pageable);
        model.addAttribute("notice",notice);
        model.addAttribute("maxPage", 5);
        return "notice";
    }

    //각 공지사항 들어가는 부분
    @GetMapping("/notice/{postNum}")
    public String list(Model model, @PathVariable("postNum") Long postNum) {
        List<Notice> notices = noticeService.findOne(postNum);
        model.addAttribute("notice",notices);
        return "noticePage";
    }



}