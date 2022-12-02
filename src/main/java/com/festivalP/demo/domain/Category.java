package com.festivalP.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name="interest_code")
    private Long interestCode;

    @Column(name="interest_text")
    private String interestText;



}
