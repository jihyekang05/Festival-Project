package com.festivalP.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoritePK implements Serializable {
    private Long post_num;
    private Long member_index;

}
