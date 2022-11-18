package com.festivalP.demo.controller;

//import com.festivalP.demo.domain.Contact;
import com.festivalP.demo.domain.Contact;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.service.FestivalService;
//import com.festivalP.demo.service.MailService;
//import com.festivalP.demo.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@Log4j2
@Controller
@RequiredArgsConstructor
public class PostController {


    private final FestivalService festivalService;

//    private final MailService mailService;

    //전체 축제리스트 불러오는 부분
    @GetMapping("/allfestival")
    public String list(Model model, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable) {
//        List<Posts> festivals = festivalService.findFestivals();
        Page<Posts> festivals = festivalService.paging2( pageable);
        model.addAttribute("posts",festivals);
//        System.out.println();
        return "every_festival_board";
    }

    //페이지 ajax
    @PostMapping("/allfestival/scroll")
    @ResponseBody
    public Page<Posts> list(Model model, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable, String  keyword) {
        System.out.println("=========================");
//        System.out.println(page);
        System.out.println(pageable.getPageNumber());
        Page<Posts> festivals = festivalService.paging(keyword, pageable);
//        model.addAttribute("posts", festivals);
        System.out.println(festivals.getTotalPages()); //2
        return festivals;
    }



    //각 축제별 정보와 리뷰리스트 불러오는 부분
    @GetMapping("/festival/{postNum}")
    public String list(Model model, @PathVariable("postNum") Long postNum) {
        List<Posts> post = festivalService.findOne(postNum);
        model.addAttribute("post", post);
        List<Review> reviews = festivalService.findReviews(postNum);
        model.addAttribute("reviews",reviews);
        return "Each_Festival_board";
    }


    //후기 제출했을 때 데이터 저장되는 부분
    @PostMapping("/festival/review")
    @ResponseBody
    public HashMap<String, Object> Review(@RequestBody Review data) {
        festivalService.saveReview(data);
        System.out.println(festivalService.saveReview(data));
        return null;
    }

    //검색
    @GetMapping("/allfestival/search")
    public String search(String keyword, Model model){
        List<Posts> postsList = festivalService.searchPosts(keyword);
        model.addAttribute("posts", postsList);
        return "every_festival_board";
    }

    //지역별 축제
    @GetMapping("/localFestival")
    public String local() {
        return "Local_Festival_board";
    }

    //지역주소 받아오고 지역별 축제 보여주기
    @PostMapping("/local")
    @ResponseBody
    public List local_Addr(Long local) {
        List<Posts> addr = festivalService.findOne2(local);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(addr.size());
        return addr;
    }

    //contact
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }








//=============================================================================
//    @GetMapping("/festival/{postNum}")
//    public String createReview(Model model){
//        model.addAttribute("review", new review());
//        return "Each_Festival_board";
//    }


//    @PostMapping("/festival/{postNum}/stars")
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
