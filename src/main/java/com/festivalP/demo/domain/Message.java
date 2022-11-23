package com.festivalP.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity // 테이블과 링크 될 클래스
@Table(name = "MESSAGE")
@Getter
@Setter
public class Message {


    @Id // 해당 테이블의 PK 항목
    @GeneratedValue // PK 생성 규칙
    @Column(name="message_index")// 테이블의 칼럼,
    private Long messageIndex;

    @Column(name="member_id")
    private String memberIndex;

    @Column(name="chatroom_id")
    private String chatroomId;

    @Column(name="message")
    private String message;

    @Column(name="message_create_date")
    private Date messageCreateDate;
    
}
