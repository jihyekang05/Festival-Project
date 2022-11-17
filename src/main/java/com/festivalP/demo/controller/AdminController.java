package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Notice;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.form.AuthInfo;
import com.festivalP.demo.form.FestivalForm;
import com.festivalP.demo.service.FestivalService;
import com.festivalP.demo.service.MemberService;
import com.festivalP.demo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {





    private final FestivalService festivalService;
    private final MemberService memberService;
//
    private final NoticeService noticeService;

    @RequestMapping("/admin")
    public String festivalManagement(Model model, HttpSession session) {
    // 관리자 메인 페이지 (페스티벌 글 목록)
        List<Posts> festivals = festivalService.findFestivals();
        model.addAttribute("posts",festivals);

        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

        if(authInfo.getState() ==2){
            return "festivalManagement";
        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping("/festivalWrite")
    public String festivalWrite() {
        // 축제 작성 페이지
        return "festivalWrite";
    }

    @PostMapping("/festivalWrite")
    public String fes_create( MultipartHttpServletRequest multi) throws ParseException {
//        if (result.hasErrors()) {
//            return "members/festivalWrite";
//        }


        Posts posts = new Posts();

        posts.setAdmin_index(Long.parseLong(multi.getParameter("admin_index")));
        posts.setContent_text(multi.getParameter("content_text"));
        posts.setFestival_title(multi.getParameter("festival_title"));
        posts.setFestival_category(multi.getParameter("festival_title"));
        posts.setBoard_addr(multi.getParameter("address"));
        posts.setBoard_loc_addr(Long.parseLong(multi.getParameter("admin_index")));

        MultipartFile file = multi.getFile("content_image");
        String filename = file.getOriginalFilename();

        String uploadDir = "D:\\upload"+File.separator;
        File uploadFolder = new File(uploadDir);
        if(!uploadFolder.exists()){
            uploadFolder.mkdir();
        }

        String fullPath = uploadDir + filename;
        try {
            file.transferTo(new File(fullPath));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // 문자열 -> Date
        Date date = formatter.parse(multi.getParameter("festival_upload_date"));

        posts.setFestival_upload_date(date);

        posts.setContent_views(0L);
        posts.setReview_score_avg(0L);


        festivalService.join(posts);


         return "redirect:/admin";
    }


    @RequestMapping("/noticeManagement")
    public String noticeManagement(Model model) {
        List<Notice> notice = noticeService.findNotice();
        model.addAttribute("notice",notice);
    // 공지관리
        return "noticeManagement";
    }

    @RequestMapping("/noticeManagementDetail")
    public String noticeManagementDetail() {
    // 공지 관리 상세 사항
        return "noticeManagementDetail";
    }

    @RequestMapping("/memberManagement")
    public String memberManagement(Model model) {
    // 회원관리
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members",members);

        return "memberManagement";
    }
//
//    @RequestMapping("/memberManagement")
//    public String memberManagement(Model model) {
//    // 회원관리
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members",members);
//
//        return "memberManagement";
//    }


    @RequestMapping("/noticeWrite")
    public String noticeWrite() {
    // notice 작성 창, 수정 창 같이 사용해도 될까요?
    // 공지 작성/수정 페이지


        return "noticeWrite";
    }

    @PostMapping("/noticeWrite")
    public String noticeCreate(Notice form, BindingResult result){
        Notice notice = new Notice();

        notice.setPost_num(form.getPost_num());
        notice.setAdmin_index(1L);
        notice.setContent_title(form.getContent_title());
        notice.setContent_text(form.getContent_text());

        noticeService.join(notice);

        return "redirect:/noticeManagement";
    }


//    @PostMapping("/festivalManagement")
//    public Long del_post_num(Long post_num) {
//
//        festivalService.deleteByPost_num(post_num);
//
//        return "redirect:/admin";
//    }

    @GetMapping("/admin/delete/{post_num}")
    public String del_post_num(@PathVariable("post_num") Long post_num) {
        System.out.println(post_num);
        int result = festivalService.deleteByPost_num(post_num);
        System.out.println("result : "+result);

        return "redirect:/admin";
    }



    @GetMapping("/admin/modify/{post_num}")
    public String modify_post_num(Model model,@PathVariable("post_num") Long post_num) {
        List<Posts> festivals = festivalService.findOne(post_num);
        model.addAttribute("posts",festivals);

        return "festivalModify";
    }

    @PostMapping("/admin/modify/{post_num}")
    public String fes_Modify(@PathVariable("post_num") Long post_num,  MultipartHttpServletRequest multi) throws ParseException {

        Posts posts = new Posts();
        posts.setPost_num(post_num);

        posts.setAdmin_index(Long.parseLong(multi.getParameter("admin_index")));
        posts.setContent_text(multi.getParameter("content_text"));
        posts.setFestival_title(multi.getParameter("festival_title"));
        posts.setFestival_category(multi.getParameter("festival_title"));
        posts.setBoard_addr(multi.getParameter("address"));
        posts.setBoard_loc_addr(Long.parseLong(multi.getParameter("admin_index")));

        MultipartFile file = multi.getFile("content_image");
        String filename = file.getOriginalFilename();

        String uploadDir = "D:\\upload"+File.separator;
        File uploadFolder = new File(uploadDir);
        if(!uploadFolder.exists()){
            uploadFolder.mkdir();
        }

        String fullPath = uploadDir + filename;
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    //date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(multi.getParameter("festival_upload_date"));

        posts.setFestival_upload_date(date);

        posts.setContent_views(0L);
        posts.setReview_score_avg(0L);

        System.out.println("posts ================"+posts);
        festivalService.modifyByPost_num(posts);

        return "redirect:/admin";
    }



}
