package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.service.FestivalService;
import com.festivalP.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/post")
public class PostController {

    private final FestivalService festivalService;


    @GetMapping("/allfestival")
    public String list(Model model) {
        List<Posts> festivals = festivalService.findFestivals();
        model.addAttribute("posts",festivals);
        return "every_festival_board";
    }

    @GetMapping("/festival/{post_num}")
    public String list(Model model, @PathVariable("post_num") Long post_num) {
        List<Posts> post = festivalService.findOne(post_num);
        model.addAttribute("post", post);
        System.out.println("-----------------------------------");
        System.out.println(post);
        return "Each_Festival_board";
    }

//    @GetMapping("/favorites")
//    public String Favorites(Model model) {
//        List<Posts>
//    }




    //    @RequestMapping("/contact")
//    public String contact() {
//
//        return "contact";
//    }

//    @RequestMapping("/postDetail")
//    public String postDetail() {
//
//        return "postDetail";
//    }
//
//    @RequestMapping("/notice")
//    public String notice() {
//
//        return "notice";
//    }
//
//    @RequestMapping("/noticeDetail")
//    public String noticeDetail() {
//
//        return "noticeDetail";
//    }
//
//    @RequestMapping("/favoriteFestival")
//    public String favoriteFestival() {
//
//        return "favoriteFestival";
//    }
//
//    @RequestMapping("/localFestival")
//    public String localFestival() {
//
//        return "localFestival";
//    }
}
