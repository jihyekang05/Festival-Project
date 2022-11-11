package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.festival;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
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

    public List<Posts> deleteByPost_num(Long post_num){
        return em.createQuery("delete p from Posts p where p.post_num = :post_num", Posts.class).setParameter("post_num",post_num).getResultList();
    }

    //제목 검색할 때 필요
    public List<Posts> findByFestival_Title(String festival_title) {
        return em.createQuery("select p from Posts p where p.festival_title LIKE :festival_title", Posts.class).setParameter("festival_title", festival_title).getResultList();
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



}
