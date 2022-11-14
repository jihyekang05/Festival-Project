package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public void save(Review review) {em.persist(review);}

    public List<Review> findAllReview(Long post_num) {
        return em.createQuery("select r from Review r where r.post_num = :post_num", Review.class).setParameter("post_num",post_num).getResultList();
        //return em.createQuery("select p from Posts p where p.post_num = :post_num", Posts.class).setParameter("post_num",post_num).getResultList();
    }
}
