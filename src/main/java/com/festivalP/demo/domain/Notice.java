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
    private Long post_num;

    private Long admin_index;

    private String content_title;

    private String content_text;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date notice_date;
}
