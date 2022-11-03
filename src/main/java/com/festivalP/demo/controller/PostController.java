package com.festivalP.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {



    @RequestMapping("/contact")
    public String contact() {

        return "contact";
    }

    @RequestMapping("/allFestival")
    public String allFestival() {

        return "allFestival";
    }

    @RequestMapping("/postDetail")
    public String postDetail() {

        return "postDetail";
    }

    @RequestMapping("/notice")
    public String notice() {

        return "notice";
    }

    @RequestMapping("/noticeDetail")
    public String noticeDetail() {

        return "noticeDetail";
    }

    @RequestMapping("/favoriteFestival")
    public String favoriteFestival() {

        return "favoriteFestival";
    }

    @RequestMapping("/localFestival")
    public String localFestival() {

        return "localFestival";
    }
}
