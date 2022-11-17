package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Admin;
import com.festivalP.demo.domain.Member;

import com.festivalP.demo.form.AdminForm;
import com.festivalP.demo.form.AuthInfo;
import com.festivalP.demo.form.MemberForm;
import com.festivalP.demo.service.AdminService;
import com.festivalP.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

//import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AdminService adminService;
    public WeakHashMap<String, String> authData = new WeakHashMap<>();



    //////////////////
    // 어드민 회원가입 페이지
    @GetMapping("/member/adminsignup")
    public String createAdminForm(Model model){
        model.addAttribute("adminForm", new AdminForm());
        // resource 의 HTML 경로
            return "member/adminSignUpForm";
    }

    @PostMapping("/member/adminsignup")
    public String create(AdminForm adminForm, BindingResult result){
        if(result.hasErrors()){
            return "member/adminSignUpForm";
        }

        Admin admin = new Admin();
        admin.setAdmin_id(adminForm.getId());
        admin.setAdmin_pw(adminForm.getPw());

        adminService.join(admin);
        System.out.println("관리자 회원가입성공");
        // 회원가입 완료 시 리턴 페이지
        return "redirect:/";
    }

    
    ////////////////////////////////
    // admin 아이디 중복체크
    @ResponseBody
    @PostMapping("/admin/iddupcheck")
    public String adminiddupcheck(String admin_id){

        if(adminService.validateDuplicateAdminId(admin_id)){
            return "S";
        }
        else{
            return "F";
        }
    }
    
    
    

    /////////////////////////
    // 멤버 회원가입
    @GetMapping("/member/signup")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        // resource 의 HTML 경로
        return "member/signUpForm";
    }

    @PostMapping("/member/signup")
    public String create(MemberForm form, BindingResult result){
        if(result.hasErrors()){
            return "member/signUpForm";
        }

        Member member = new Member();
        member.setMember_id(form.getId());
        member.setMember_pw(form.getPw());
        member.setMember_birth(form.getBirth());
        member.setMember_addr(form.getAddr());
        member.setMember_email(form.getEmail());
        member.setMember_nickname(form.getNickname());
        member.setMember_category(form.getCategory());
        member.setMember_state(form.getState());

        memberService.join(member);
        System.out.println("회원가입성공");
        // 회원가입 완료 시 리턴 페이지
        return "redirect:/";
    }

    @PostMapping("/member/delete")
    public String delete(HttpSession session){

        Member member = (Member) session.getAttribute("member");
        memberService.deleteMember(member);
        session.invalidate();
        return "redirect:/";
    }



    // 마이페이지
    @GetMapping("/member/mypage")
    public String mypage(HttpSession session, Model model){


        try{
            AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");

            Member member = (Member) session.getAttribute("member");

            return "member/myPageForm";
        }catch(Exception e){

            return "redirect:/";
        }
    }

    @ResponseBody
    @PostMapping("/member/mypage/modifyconfirm")
    public String modify(@RequestParam("nickname")String nickname, @RequestParam("addr")String addr, @RequestParam("birth")Date birth, @RequestParam("category")String category, @RequestParam("email")String email, HttpSession session){

        Member member = (Member) session.getAttribute("member");

        System.out.println(member.getMember_nickname());
        System.out.println(member.getMember_addr());
        System.out.println(member.getMember_birth());
        System.out.println(member.getMember_category());
        System.out.println(member.getMember_email());


        member.setMember_nickname(nickname);
        member.setMember_addr(addr);
        member.setMember_birth(birth);
        member.setMember_email(email);
        member.setMember_category(category);

        session.setAttribute("member", memberService.updateInfo(member));
        System.out.println(member.getMember_nickname());
        System.out.println(member.getMember_addr());
        System.out.println(member.getMember_birth());
        System.out.println(member.getMember_category());
        System.out.println(member.getMember_email());

        System.out.println("$$$ wowwowow modifyyyy");
        return "modify success";
    }

    @ResponseBody
    @PostMapping("/member/mypage/modify")
    public String modify(@RequestParam("login_id")String login_id, @RequestParam("login_password")String login_password){

        if (memberService.memberExistCheck(login_id, login_password)) {

            try {
                return "S";
            } catch (Exception e) {
                System.out.println("login failed: "+e.toString());
                return "F";

            }
        }
        else {
            return "F";
        }
    }

    @GetMapping("/member/modifypage")
    public String modifypage(HttpSession session){

        Member member = (Member)session.getAttribute("member");

        System.out.println(member.getMember_id());
        System.out.println(member.getMember_pw());
        return "member/memberModifyPage";
    }

    // 아이디 중복체크
    @ResponseBody
    @PostMapping("/member/iddupcheck")
    public String iddupcheck(String member_id){

        if(memberService.validateDuplicateMemberId(member_id)){
            return "S";
        }
        else{
            return "F";
        }
    }

    // 닉네임 중복체크
    @ResponseBody
    @PostMapping("/member/nicknamedupcheck")
    public String nicknamedupcheck(String member_nickname){

        if(memberService.validateDuplicateMemberNickname(member_nickname)){
            return "S";
        }
        else{
            return "F";
        }
    }

    @Autowired
    JavaMailSender mailSender;

    
    // 인증메일 발송
    @ResponseBody
    @PostMapping("/member/emailAuth")
    public String emailAuth(String email){
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;

        // 메일 발송
        String setFrom = "wowinteresting234@gmail.com";
        String toMail = email;
        String title = "회원가입 인증 메일입니다.";
        String content =
                "안녕하세요. XX방문을 감사드립니다."+
                        "<br><br>"+
                        "인증번호는 "+checkNum+" 입니다."+
                        "<br>"+
                        "해당 번호를 입력하여 인증을 진행해주세요.";
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

        authData.put(email, Integer.toString(checkNum));
        return Integer.toString(checkNum);
    }

    // 이메일 인증번호 체크
    @ResponseBody
    @PostMapping("/member/emailAuthCheck")
    public String emailAuthCheckFunction(String email, String emailAuthValue){
        if(emailAuthValue.equals(authData.get(email))){
            authData.replace(email, null);
            System.gc();
            return "S";
        }
        else{
            return "F";
        }
    }


    @RequestMapping("/findid")
    public String findId() {

        return "findId";
    }

}