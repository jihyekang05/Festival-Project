package com.festivalP.demo.service;


import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.repository.FestivalRepository;
import com.festivalP.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long join(Posts posts) {

        festivalRepository.save(posts);
        return posts.getPost_num();
    }

    public List<Posts> findFestivals() {
        return festivalRepository.findAll();
    }


    public List<Posts> findOne(Long post_num) {
        return festivalRepository.findByPost_num(post_num);
    }

    @Transactional
    public Long saveReview(Review review) {

        reviewRepository.save(review);
        return review.getReview_index();
    }

    public List<Review> findReviews() {
        System.out.println("===========");
        System.out.println("동작함!");
        return reviewRepository.findAllReview();}

}