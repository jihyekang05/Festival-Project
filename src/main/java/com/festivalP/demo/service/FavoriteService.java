package com.festivalP.demo.service;

import com.festivalP.demo.domain.Favorite;
import com.festivalP.demo.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Transactional
    public String insert(Favorite favorite){

        System.out.println("FavoritService.insert");
        favoriteRepository.save(favorite);

        return "";
    }

    @Transactional
    public boolean favoriteExist(Long member_index, Long post_num){

        Favorite favorite =favoriteRepository.existCheck(member_index, post_num);

        if(favorite==null){
            return false;
        }
        return true;
    }

    @Transactional
    public void delete(Favorite favorite) {

        favoriteRepository.delete(favorite);

    }
}
