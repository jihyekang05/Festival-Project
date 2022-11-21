package com.festivalP.demo.repository;


import com.festivalP.demo.domain.Favorite;
import com.festivalP.demo.domain.FavoritePK;
import com.festivalP.demo.domain.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Repository
@Transactional
@RequiredArgsConstructor
public class FavoriteRepository {

    private final EntityManager em;

    public void save(Favorite favorite) {
        System.out.println("FavoriteRepository.save");
        System.out.println("post_num : "+favorite.getPost_num());
        System.out.println("member_index: "+favorite.getMember_index());

        em.persist(favorite);
    }

    public void delete(Favorite favorite){

//        EntityTransaction transaction = em.getTransaction();
        FavoritePK favoritePK = new FavoritePK();
        favoritePK.setMember_index(favorite.getMember_index());
        favoritePK.setPost_num(favorite.getPost_num());


//        transaction.begin();
        Favorite resFavorite = em.find(Favorite.class, favoritePK);
        em.remove(resFavorite);
//        transaction.commit();
    }

    public Favorite existCheck(Long member_index, Long post_num){

        FavoritePK favoritePK = new FavoritePK();
        favoritePK.setMember_index(member_index);
        favoritePK.setPost_num(post_num);

        try{
            Favorite favorite =em.find(Favorite.class,favoritePK);
            return favorite;
        }
        catch(Exception e){
            return null;
        }
    }
}
