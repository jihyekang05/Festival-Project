package com.festivalP.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Contact")
@Table
@Getter @Setter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue
    private Long contact_index;

    private String contact_name;

    private String contact_email;

//    private String contact_number;

    private String contact_text;
}
