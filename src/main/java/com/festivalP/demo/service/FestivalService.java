package com.festivalP.demo.service;


import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.repository.FestivalRepository;
import com.festivalP.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    //오래된 순 정렬
    public List<Posts> sortOldFestivals() { return festivalRepository.findAllOrderByFestival_Upload_Date_Old();}

    //최신 순 정렬
    public List<Posts> sortNewFestivals() {return festivalRepository.findAllOrderByFestival_Upload_Date_New();}

    //조회수 정렬
    public List<Posts> sortViewFestivals() {return festivalRepository.findAllOrderByFestival_Content_Views();}

    public List<Posts> findOne(Long post_num) {
        return festivalRepository.findByPost_num(post_num);
    }


    @Transactional
    public Long saveReview(Review review) {

        reviewRepository.save(review);
        return review.getReview_index();
    }

    public List<Review> findReviews(Long post_num) {
        return reviewRepository.findAllReview(post_num);}


    public List<Posts> deleteByPost_num(Long post_num) {
        return festivalRepository.deleteByPost_num(post_num);
    }

    //검색기능
    @Transactional
    public List<Posts> searchPosts(String keyword) {
        List<Posts> posts = festivalRepository.findByFestival_Title(keyword);


        if(keyword.isEmpty()) return findFestivals();
        else {
            return posts;
        }
    }



}