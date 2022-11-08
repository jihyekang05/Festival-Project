package com.festivalP.demo.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    private String id;
    private String pw;
    private String nickname;
    private String birth;
    private String email;
    private String addr;
    private String category;
    private int state;
}

