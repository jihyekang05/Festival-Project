package com.festivalP.demo.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    private String id;
    private Long pw;
    private String nickname;
    private String email;
    private String addr;
    private String category;


}