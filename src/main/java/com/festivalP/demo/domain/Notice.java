package com.festivalP.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name="Notice")
@Table
@Getter
@Setter
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue
    @Column(name="post_num")
    private Long postNum;

    @Column(name="admin_index")
    private Long adminIndex;


    @Column(name="content_title")
    private String contentTitle;

    @Column(name="content_text")
    private String contentText;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="notice_date")
    private Date noticeDate;
}
