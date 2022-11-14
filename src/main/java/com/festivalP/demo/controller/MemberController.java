package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Member;

import com.festivalP.demo.form.AuthInfo;
import com.festivalP.demo.form.MemberForm;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;

//import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    public WeakHashMap<String, String> authData = new WeakHashMap<>();



    // 회원가입 페이지
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
        System.out.println("form.state: "+form.getState());
        member.setMember_id(form.getId());
        member.setMember_pw(form.getPw());
        member.setMember_birth(form.getBirth());
        member.setMember_addr(form.getAddr());
        member.setMember_email(form.getEmail());
        member.setMember_nickname(form.getNickname());
        member.setMember_category(form.getCategory());
        member.setMember_state(form.getState());

        System.out.println("member.state: "+member.getMember_state());
        memberService.join(member);
        System.out.println("회원가입성공");
        // 회원가입 완료 시 리턴 페이지
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

            return "/";
        }


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
        String setFrom = "wowinteresting@naver.com";
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

    @RequestMapping("/mypage")
    public String myPage(){

        return "myPage";
    }
}