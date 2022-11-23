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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String list(Model model, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable,String keyword) {
//        List<Posts> festivals = festivalService.findFestivals();
        System.out.println("allfestival keyword");
        System.out.println(keyword);
        System.out.println(pageable.getPageNumber());
        Page<Posts> festivals = null;
        if(keyword == null) {
            festivals = festivalService.paging2(pageable);
        } else {
            festivals = festivalService.paging( keyword, pageable);
        }
        model.addAttribute("posts",festivals);
        model.addAttribute("keyword", keyword);
//        System.out.println();
        return "every_festival_board";
    }



    //페이지 ajax
    @PostMapping("/allfestival/scroll")
    @ResponseBody
    public Page<Posts> list(Model model, String keyword, @PageableDefault(size =6,page=0, direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("=========================");
//        System.out.println(page);
        System.out.println("scroll page keyword :" + keyword);
        Page<Posts> festivals = null;
        if(keyword == null) {
            festivals = festivalService.paging2(pageable);
        } else {
            festivals = festivalService.paging( keyword, pageable);
        }
//        model.addAttribute("posts", festivals);
        System.out.println(festivals.getTotalPages()); //2
        return festivals;
    }



    //각 축제별 정보와 리뷰리스트 불러오는 부분
//    @GetMapping("/festival/{post_num}")
//    public String list(Model model, @PathVariable("post_num") Long post_num, HttpServletRequest request) {
//        List<Posts> post = festivalService.findOne(post_num);
//
////        model.addAttribute("post", post);
//        List<Review> reviews = festivalService.findReviews(post_num);
//
//        return "every_festival_board";
//    }
    @GetMapping("/festival/{postNum}")
    public String list(Model model, @PathVariable("postNum") Long postNum, HttpServletRequest request) {
        List<Posts> post = festivalService.findOne(postNum);
        model.addAttribute("post", post);
        List<Review> reviews = festivalService.findReviews(postNum);
        model.addAttribute("reviews",reviews);

        PostForm postForm = new PostForm();

        System.out.println("@@@@@@@@@@@@@@ " +post.get(0).getPostNum());

        postForm.setPostNum(post.get(0).getPostNum());
        postForm.setAdminIndex(post.get(0).getAdminIndex());
        postForm.setContentText(post.get(0).getContentText());
        postForm.setContentViews(post.get(0).getContentViews());
        postForm.setFestivalTitle(post.get(0).getFestivalTitle());
        postForm.setReviewScoreAvg(post.get(0).getReviewScoreAvg());
        postForm.setBoardAddr(post.get(0).getBoardAddr());
        postForm.setBoardLocAddr(post.get(0).getBoardLocAddr());
        postForm.setContentImage(post.get(0).getContentImage());
        postForm.setFestivalUploadDate(post.get(0).getFestivalUploadDate());
        postForm.setProgressState(post.get(0).getProgressState());
        postForm.setFestivalCategory(post.get(0).getFestivalCategory());

        HttpSession session = request.getSession();

        Member member = (Member) session.getAttribute("member");
        if(member!=null){
            postForm.setFavoriteFlag(favoriteService.favoriteExist(member.getMemberIndex(), postNum));
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
//    @GetMapping("/allfestival/search")
//    public String search(String keyword, Model model){
//        List<Posts> postsList = festivalService.searchPosts(keyword);
//        model.addAttribute("posts", postsList);
//        return "every_festival_board";
//    }

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
