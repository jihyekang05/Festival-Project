package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FestivalService festivalService;



    //가장 많이 찾는 축제
    @GetMapping("/")
    public String home(Model model) {
        List<Posts> topThree = festivalService.sort3ViewFestivals();
        List<Posts> topDate =  festivalService.sort3NewFestivals();
        System.out.println("here!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(topThree.size());
//        for (Posts p:topThree) {
//            System.out.println(p.getcontentViews());
//
//        }
        model.addAttribute("topview",topThree);
        model.addAttribute("topdate",topDate);
        return "home";
    }






}

