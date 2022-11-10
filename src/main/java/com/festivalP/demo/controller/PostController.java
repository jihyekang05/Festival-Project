package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        List<Review> reviews = festivalService.findReviews();
        model.addAttribute("reviews",reviews);
        return "Each_Festival_board";
    }

    //후기 제출했을 때 데이터 저장되는 부분
    @PostMapping("/festival/review")
    @ResponseBody
    public HashMap<String, Object> Review(@RequestBody Review data) {
        festivalService.saveReview(data);
        return null;
    }





//=============================================================================
//    @GetMapping("/festival/{post_num}")
//    public String createReview(Model model){
//        model.addAttribute("review", new review());
//        return "Each_Festival_board";
//    }


//    @PostMapping("/festival/{post_num}/stars")
//    public void funct(@RequestParam int starCnt) {
//
////        service.star(starCnt); // -> service에서는 repository.
//
//    }





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
