package com.festivalP.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="FESTIVAL_REVIEW")
@Getter @Setter
@NoArgsConstructor
public class review {

    @Id
    private Long review_index;

    private Long post_num;

    private Long member_index;

    private String review_text;

    private Long review_score;
}
