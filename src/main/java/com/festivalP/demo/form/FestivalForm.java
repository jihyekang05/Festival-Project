package com.festivalP.demo.form;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

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

    private Long board_loc_addr; //지역주소

    private String content_image;

    private String progress_state; //진행상태

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date festival_upload_date;

    private String festival_category;


}
