package com.festivalP.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column
    private Long member_index;


    private String member_id;
    private Long member_pw;
    private String member_birth;
    private String member_addr;
    private String member_email;
    private String member_nickname;
    private String member_state;
    private String member_category;



//    @JsonIgnore
//    @OneToMany(mappedBy = "member")
//    private List<Order> favorite = new ArrayList<>();

}
