package com.festivalP.demo.controller;

import com.festivalP.demo.domain.Member;

import com.festivalP.demo.form.MemberForm;
import com.festivalP.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/login")
    public String loginPage() {

        return "login";
    }


    // 회원가입 페이지
    @GetMapping("/signup")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        // resource 의 HTML 경로
            return "member/signUpForm";
    }



    @PostMapping("/signup")
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

        memberService.join(member);
        System.out.println("회원가입성공");
        // 회원가입 완료 시 리턴 페이지
        return "member/signUpForm";
    }



    //    @RequestMapping(value = "/iddupcheck", method = RequestMethod.POST)
    @ResponseBody
    @PostMapping("/iddupcheck")
    public String iddupcheck(String member_id){

        System.out.print("&&&&&&&&&&& id dup check called *************");
        if(memberService.validateDuplicateMemberId(member_id)){
            return "S";
        }
        else{
            return "F";
        }
    }

    @ResponseBody
    @PostMapping("/nicknamedupcheck")
    public String nicknamedupcheck(String member_nickname){

        System.out.print("&&&&&&&&&&& nick dup check called *************");
        if(memberService.validateDuplicateMemberNickname(member_nickname)){
            return "S";
        }
        else{
            return "F";
        }
    }


    @RequestMapping("/member/signup")
    public void signup(@ModelAttribute Member member){


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