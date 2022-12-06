package com.festivalP.demo.controller;


import com.festivalP.demo.domain.Favorite;
import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.service.FavoriteService;
import com.festivalP.demo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @ResponseBody
    @PostMapping("/favoritemodify")
    public String favoriteAdd(Long post_num, Long member_index){


        System.out.println("@@@@@@@@@@@@@@ favoriteModify controller");

        try{

            Favorite favorite = new Favorite();

            favorite.setPostNum(post_num);
            favorite.setMemberIndex(member_index);

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
    public String list(Model model, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable,String keyword, HttpSession session) {

        Member member = (Member)session.getAttribute("member");
        Page<Posts> festivals = null;

        if(member==null)
            return "redirect:/";
        if(keyword == null) {
            festivals = favoriteService.paging2(member.getMemberIndex(), pageable);
            List<Posts> festivalsList = festivals.getContent();

            for(Posts p : festivalsList){
                System.out.println("$$ postNum :: "+p.getPostNum());
                System.out.println("$$ festivalTitle :: "+p.getFestivalTitle());
            }

        } else {
            festivals = favoriteService.paging(member.getMemberIndex(), keyword, pageable);
        }
        model.addAttribute("posts",festivals);
        model.addAttribute("keyword", keyword);

        return "favorite_festival_board";
    }

    @PostMapping("/favoritefestival/scroll")
    @ResponseBody
    public Page<Posts> list(Model model, String keyword, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
        System.out.println("=========================");
        System.out.println("scroll page keyword :" + keyword);

        Member member = (Member)session.getAttribute("member");


        Page<Posts> festivals=null;
        if(keyword == null) {
            festivals = favoriteService.paging2(member.getMemberIndex(),pageable);
        } else {
            festivals = favoriteService.paging(member.getMemberIndex(), keyword, pageable);
        }
        System.out.println(festivals.getTotalPages()); //2
        return festivals;
    }
}
