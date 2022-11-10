package com.festivalP.demo.form;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter@Setter
public class FestivalForm {
    private Long post_num;

    private Long admin_index;

    private String content_text;

    private Long content_views;

    @Column(name = "festival_title")
    private String festival_title;

    private Long review_score_avg ;

    private String board_addr; //주소

    private String board_loc_addr; //지역주소

    private String content_image;

    private String progress_state; //진행상태

    private String festival_upload_date;

    private String festival_category;


}
