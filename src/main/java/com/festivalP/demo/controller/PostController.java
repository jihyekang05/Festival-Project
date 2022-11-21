package com.festivalP.demo.controller;

//import com.festivalP.demo.domain.Contact;
import com.festivalP.demo.domain.Contact;
import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.form.PostForm;
import com.festivalP.demo.service.FavoriteService;
import com.festivalP.demo.service.FestivalService;
//import com.festivalP.demo.service.MailService;
import com.festivalP.demo.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
@Log4j2
@Controller
@RequiredArgsConstructor
public class PostController {

    private final FestivalService festivalService;
    private final FavoriteService favoriteService;

//    private final MailService mailService;

    //전체 축제리스트 불러오는 부분
    @GetMapping("/allfestival")
    public String list(Model model) {
        List<Posts> festivals = festivalService.findFestivals();
        model.addAttribute("posts",festivals);
        log.info("DEBUG");
        return "every_festival_board";
    }

    //각 축제별 정보와 리뷰리스트 불러오는 부분
    @GetMapping("/festival/{post_num}")
    public String list(Model model, @PathVariable("post_num") Long post_num, HttpServletRequest request) {
        List<Posts> post = festivalService.findOne(post_num);


//        model.addAttribute("post", post);

        List<Review> reviews = festivalService.findReviews(post_num);

        model.addAttribute("reviews",reviews);

        PostForm postForm = new PostForm();

        System.out.println("post.get(0): "+post.get(0));
        System.out.println("post.get(0): "+post.get(0).getContent_text());
        System.out.println("post.get(0): "+post.get(0).getContent_image());

        System.out.println("@@@@@@@@@@@@@@ " +post.get(0).getPost_num());

        postForm.setPost_num(post.get(0).getPost_num());
        postForm.setAdmin_index(post.get(0).getAdmin_index());
        postForm.setContent_text(post.get(0).getContent_text());
        postForm.setContent_views(post.get(0).getContent_views());
        postForm.setFestival_title(post.get(0).getFestival_title());
        postForm.setReview_score_avg(post.get(0).getReview_score_avg());
        postForm.setBoard_addr(post.get(0).getBoard_addr());
        postForm.setBoard_loc_addr(post.get(0).getBoard_loc_addr());
        postForm.setContent_image(post.get(0).getContent_image());
        postForm.setFestival_upload_date(post.get(0).getFestival_upload_date());
        postForm.setProgress_state(post.get(0).getProgress_state());
        postForm.setFestival_category(post.get(0).getFestival_category());

        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute("member");
        if(member!=null){
            postForm.setFavoriteFlag(favoriteService.favoriteExist(member.getMember_index(), post_num));
        }else{
            postForm.setFavoriteFlag(false);
        }
        model.addAttribute("post", postForm);

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
        return addr;
    }

    //contact
    @GetMapping("/contact")
    public String contact() {
        return "contact";
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
