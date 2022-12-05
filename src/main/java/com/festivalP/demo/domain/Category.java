package com.festivalP.demo.domain;

<<<<<<< HEAD
=======

>>>>>>> d292b217cccd77f706e17fb88142557db9d95e9d
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
import javax.persistence.*;

@Entity // 테이블과 링크 될 클래스
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
>>>>>>> d292b217cccd77f706e17fb88142557db9d95e9d
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
<<<<<<< HEAD
@IdClass(CategoryPK.class)
public class Category {

    @Id
    @Column(name="member_index")
    private Long memberIndex;

    @Id
    @Column (name="categoryClass")
    private String categoryClass;
=======
public class Category {

    @Id
    @Column(name="interest_code")
    private Long interestCode;

    @Column(name="interest_text")
    private String interestText;



>>>>>>> d292b217cccd77f706e17fb88142557db9d95e9d
}
