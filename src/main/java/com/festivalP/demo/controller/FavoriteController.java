package com.festivalP.demo.controller;


import com.festivalP.demo.domain.Favorite;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.service.FavoriteService;
import com.festivalP.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final FestivalService festivalService;


    @ResponseBody
    @PostMapping("/favoriteModify")
    public String favoriteAdd(Long post_num, Long member_index){


        System.out.println("@@@@@@@@@@@@@@ favoriteModify controller");

        try{

            Favorite favorite = new Favorite();

            favorite.setPost_num(post_num);
            favorite.setMember_index(member_index);

            if(favoriteService.favoriteExist(member_index, post_num)){

                favoriteService.delete(favorite);
                return "E";
            }
            else{
                favoriteService.insert(favorite);
                return "S";
            }

        }catch(Exception e){
            System.out.println("@@@@@@@@@@@@@@ Favoritemodify Exception: "+e.toString());

            return "F";
        }
    }


    @GetMapping("/favoritefestival")
    public String favoriteList(Model model) {
        List<Posts> festivals = festivalService.findFestivals();

        model.addAttribute("posts",festivals);


        return "favorite_festival_board";
    }


//    @GetMapping("/allfestival")
//    public String allList(Model model) {
//        List<Posts> festivals = festivalService.findFestivals();
//        model.addAttribute("posts",festivals);
//        log.info("DEBUG");
//        return "every_festival_board";
//    }
//
//    //각 축제별 정보와 리뷰리스트 불러오는 부분
//    @GetMapping("/festival/{post_num}")
//    public String fesContent(Model model, @PathVariable("post_num") Long post_num) {
//        List<Posts> post = festivalService.findOne(post_num);
//        model.addAttribute("post", post);
//        List<Review> reviews = festivalService.findReviews(post_num);
//        model.addAttribute("reviews",reviews);
//        return "Each_Festival_board";
//    }



}
