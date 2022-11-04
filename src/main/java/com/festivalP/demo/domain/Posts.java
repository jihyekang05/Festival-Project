package com.festivalP.demo.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "POSTS")
@Getter
@Setter
public class Posts {

    @Id
    @GeneratedValue
    @Column
    private Long post_num;


    private String admin_index;

    private String content_text;

    private String review_score_avg;

    private Long board_addr; //주소

    private String board_loc_addr; //지역주소

    private String content_image; //지역주소

    private String progress_state; //진행상태

    private String festival_title;

    private String festival_upload_date;

    private String festival_category;



}
