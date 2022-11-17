package com.festivalP.demo.controller;


import com.festivalP.demo.domain.Member;
import com.festivalP.demo.form.AuthInfo;
import com.festivalP.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {


    private final MemberService memberService;

    // 로그인
    @ResponseBody
    @PostMapping("/member/login")
    public String login(String login_id, String login_password, HttpServletRequest request) {

        System.out.println("login_id: " + login_id);
        System.out.println("login_password: " + login_password);


        if (memberService.memberExistCheck(login_id, login_password)) {

            try {

                AuthInfo authInfo = memberService.getMemberAuthInfo(login_id);
                Member member = memberService.getMemberAllInfo(login_id);

                HttpSession session = request.getSession();

                session.setAttribute("authInfo", authInfo);
                session.setAttribute("member", member);

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

    // 로그아웃
    @ResponseBody
    @PostMapping("/member/logout")
    public String logout(HttpSession session) {

        // 로그아웃 시 세션 삭제
        session.invalidate();
        return "success";

    }
}
