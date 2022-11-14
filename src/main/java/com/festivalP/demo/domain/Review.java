package com.festivalP.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="Review")
@Table(name="Festival_Review")
@Getter @Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long review_index;

    @Column(name="post_num")
    private Long post_num;

    private Long member_index;

    private String review_text;

    private Long review_score;

//    @OneToOne
////    @JoinColumn(name="member_index")
//    private Member member;




}
