package com.festivalP.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="Notice")
@Table
//@Table(name = "POSTS")

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
}
