package com.festivalP.demo.controller;


import com.festivalP.demo.domain.Admin;
import com.festivalP.demo.domain.Member;
import com.festivalP.demo.form.AuthInfo;
import com.festivalP.demo.form.MemberForm;
import com.festivalP.demo.service.AdminService;
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
    private final AdminService adminService;

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
                if(member.getMemberState()==1)
                    return "F";
                member.setMemberPw(null);
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

    @ResponseBody
    @PostMapping("/member/adminlogin")
    public String adminlogin(String login_id, String login_password, HttpServletRequest request) {

        System.out.println("login_id: " + login_id);
        System.out.println("login_password: " + login_password);


        if (adminService.adminExistCheck(login_id, login_password)) {
            try {

                AuthInfo authInfo = new AuthInfo();
                Admin admin = adminService.getAdminInfo(login_id);

                authInfo.setId(login_id);
                authInfo.setState(2);

                HttpSession session = request.getSession();
                session.setAttribute("authInfo", authInfo);
                session.setAttribute("admin", admin);

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
        return "redirect:/";
    }
}
