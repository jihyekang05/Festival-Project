package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SortController {

    private final FestivalService festivalService;

    //오래된 순 정렬
    @GetMapping("/allfestival/old")
    public String list(Model model, String old_Btn){
        List<Posts> oldPostsList = festivalService.sortOldFestivals();
        model.addAttribute("posts", oldPostsList);

        return "every_festival_board";
    }

    //최신 순 정렬
    @GetMapping("/allfestival/new")
    public String list(Model model, Posts posts){
        List<Posts> newPostsList = festivalService.sortNewFestivals();
        model.addAttribute("posts", newPostsList);
        System.out.println("여기다");
        System.out.println(newPostsList.size());
        return "every_festival_board";
    }

    //조회수 정렬
    @GetMapping("/allfestival/view")
    public String list(Model model){
        List<Posts> viewPostsList = festivalService.sortViewFestivals();
        model.addAttribute("posts", viewPostsList);
        System.out.println("여기다");
        System.out.println(viewPostsList.size());
        return "every_festival_board";
    }
}
