package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.festival;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class FestivalRepository {
    private final EntityManager em;

    public List<Posts> findAll() {
       return em.createQuery("select p from Posts p", Posts.class).getResultList();
    }

    public void save(Posts posts) {em.persist(posts);}



//    public Posts findOne(Long post_num) {
//        return em.find(Posts.class,post_num);
//    }

    public List<Posts> findByPost_num(Long post_num) {
        return em.createQuery("select p from Posts p where p.post_num = :post_num", Posts.class).setParameter("post_num",post_num).getResultList();
//        return em.createQuery("select p from Posts p where p.post_num = :post_num", Posts.class).getResultList();
    }

    public int deleteByPost_num(Long post_num){

        int result = em.createQuery ("delete from Posts p where p.post_num = :post_num")
                .setParameter("post_num",post_num).executeUpdate();
        return result;
    }

    public void modifyByPost(Posts posts){
          em.createQuery ("Update Posts p set p.admin_index= :admin_index," +
                          " p.content_text= :content_text," +
                          " p.board_loc_addr= :board_loc_addr," +
                          " p.board_addr= :board_addr," +
                          " p.content_image= :content_image," +
                          " p.festival_title= :festival_title," +
                          " p.festival_upload_date= :festival_upload_date " +
                          " where p.post_num = :post_num")
                  .setParameter("post_num", posts.getPost_num())
                  .setParameter("admin_index",posts.getAdmin_index())
                  .setParameter("content_text",posts.getContent_text())
                  .setParameter("board_loc_addr",posts.getBoard_loc_addr())
                  .setParameter("board_addr",posts.getBoard_addr())
                  .setParameter("content_image",posts.getContent_image())
                  .executeUpdate();

        //return result;
    }

}


