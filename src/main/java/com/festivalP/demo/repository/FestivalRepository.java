package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.festival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FestivalRepository {
    private final EntityManager em;

    public List<Posts> findAll() {
        return em.createQuery("select p from Posts p", Posts.class).getResultList();
    }
}
