package com.festivalP.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @RequestMapping("/login")
    public String loginPage() {

        return "login";
    }

    @RequestMapping("/signUp")
    public String signUp(){

        return "signUp";
    }

    @RequestMapping("/findId")
    public String findId() {

        return "findId";
    }

    @RequestMapping("/myPage")
    public String myPage(){

        return "myPage";
    }
}