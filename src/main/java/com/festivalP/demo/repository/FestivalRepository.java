package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.festival;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
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



    public List<Posts> findByPost_num(Long post_num) {
        return em.createQuery("select p from Posts p where p.post_num = :post_num", Posts.class).setParameter("post_num",post_num).getResultList();
    }

    public int deleteByPost_num(Long post_num){

        int result = em.createQuery ("delete from Posts p where p.post_num = :post_num")
                .setParameter("post_num",post_num).executeUpdate();
        return result;
    }


//    제목 검색할 때 필요
    public List<Posts> findByFestival_Title(String keyword) {

        return em.createQuery("select p from Posts p where p.festival_title LIKE concat('%',:keyword,'%')",Posts.class).setParameter("keyword",keyword).getResultList();
    }



    //오래된 순 정렬
    public List<Posts> findAllOrderByFestival_Upload_Date_Old() {
        return em.createQuery("select p from Posts p order by p.festival_upload_date", Posts.class).getResultList();
    }

    //최신 순 정렬
    public List<Posts> findAllOrderByFestival_Upload_Date_New() {
        return em.createQuery("select p from Posts p order by p.festival_upload_date desc", Posts.class).getResultList();
    }

    //조회수 정렬
    public List<Posts> findAllOrderByFestival_Content_Views() {
        return em.createQuery("select p from Posts p order by p.content_views desc", Posts.class).getResultList();
    }

    //지역별로 데이터찾기
    public List<Posts> findByBoard_loc_addr(Long board_loc_addr) {
        return em.createQuery("select p from Posts p where p.board_loc_addr LIKE :board_loc_addr", Posts.class).setParameter("board_loc_addr",board_loc_addr).getResultList();
    }

    //많이 찾는 축제찾기
    public List<Posts> findOneOrderByFestival_Content_Views() {
       return em.createQuery("select p from Posts p order by p.content_views desc", Posts.class).setFirstResult(0).setMaxResults(3).getResultList();
    }

    //새로운 축제찾기
    public List<Posts> findOndOrderByUpload_Date() {
        return em.createQuery("select p from Posts p order by p.festival_upload_date desc", Posts.class).setFirstResult(0).setMaxResults(3).getResultList();
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


