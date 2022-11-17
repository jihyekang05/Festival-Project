package com.festivalP.demo.repository;


import com.festivalP.demo.domain.Notice;
import com.festivalP.demo.domain.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final EntityManager em;


    public void save(Notice notice) {em.persist(notice);}

    public List<Notice> findAll() {
        return em.createQuery("select p from Notice p", Notice.class).getResultList();
    }

    public int deleteByNotice_num(Long post_num){

        int result = em.createQuery ("delete from Notice p where p.post_num = :post_num")
                .setParameter("post_num",post_num).executeUpdate();
        return result;
    }

}
