package com.festivalP.demo.repository;


import com.festivalP.demo.domain.Notice;
import com.festivalP.demo.domain.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final EntityManager em;


    public void save(Notice notice) {
        em.persist(notice);
    }

    public List<Notice> findAll() {
        return em.createQuery("select p from Notice p", Notice.class).getResultList();
    }

    //각 공지 들어갈 때
    public List<Notice> findByPost_num(Long post_num) {
        return em.createQuery("select n from Notice n where n.post_num = :post_num", Notice.class).setParameter("post_num", post_num).getResultList();
    }


    public List<Notice> findByNotice_Title(String keyword) {
        return em.createQuery("select n from Notice n where n.content_text LIKE concat('%',:keyword,'%')", Notice.class).setParameter("keyword", keyword).getResultList();
    }


    //최근공지
    public List<Notice> findByNotice_Date() {
        return em.createQuery("select n from Notice n order by n.notice_date desc", Notice.class).setFirstResult(0).setMaxResults(1).getResultList();
    }

}

