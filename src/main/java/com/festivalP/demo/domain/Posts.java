package com.festivalP.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name="Posts")
@Table
//@Table(name = "POSTS")

@Getter
@Setter
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue
    @Column(name="post_num")
    private Long post_num;

    private Long admin_index;

    private String content_text;

    private Long content_views;

    @Column(name = "festival_title")
    private String festival_title;

    private Long review_score_avg;

    private String board_addr; //주소

    private Long board_loc_addr; //지역주소

    private String content_image;

    private String progress_state; //진행상태

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date festival_upload_date;

    private String festival_category;





}
