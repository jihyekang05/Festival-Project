package com.festivalP.demo.service;


import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.domain.Review;
import com.festivalP.demo.repository.FestivalRepository;
import com.festivalP.demo.repository.PageRepository;
import com.festivalP.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FestivalService{

    private final FestivalRepository festivalRepository;
    private final ReviewRepository reviewRepository;
    private final PageRepository pageRepository;

    @Transactional
    public Long join(Posts posts) {

        festivalRepository.save(posts);
        return posts.getPostNum();
    }


    public List<Posts> findFestivals() {
        return festivalRepository.findAll();
    }



    //@Transactional(readOnly = true)
    public  Page<Posts> paging(String keyword, Pageable pageable) {
        Page<Posts> Pages= pageRepository.findByFestivalTitleContaining(keyword, pageable);
        return Pages;
    }


    //오래된 순 정렬
    public List<Posts> sortOldFestivals() { return festivalRepository.findAllOrderByfestivalUploadDate_Old();}

    //최신 순 정렬
    public List<Posts> sortNewFestivals() {return festivalRepository.findAllOrderByfestivalUploadDate_New();}

    //조회수 정렬
    public List<Posts> sortViewFestivals() {return festivalRepository.findAllOrderByFestival_contentViews();}

    public List<Posts> findOne(Long postNum) {
        return festivalRepository.findBypostNum(postNum);
    }

    //지역별축제기능
    public List<Posts> findOne2(Long boardLocAddr){ return festivalRepository.findByboardLocAddr(boardLocAddr);}

    //홈페이지 조회수 많은축제 탑3
    public List<Posts> sort3ViewFestivals() {
        System.out.println("여기@@@@@@@@@@@");
        List<Posts> posts = festivalRepository.findOneOrderByFestival_contentViews();
        System.out.println(posts);
        return posts;
    }
    //홈페이지 새로운축제 탑3
    public List<Posts> sort3NewFestivals() {
        List<Posts> date = festivalRepository.findOndOrderByUpload_Date();
        return date;
    }


    @Transactional
    public Long saveReview(Review review) {
        reviewRepository.save(review);
        return review.getReviewIndex();
    }

    public List<Review> findReviews(Long postNum) {
        return reviewRepository.findAllReview(postNum);}



    //검색기능
    @Transactional
    public List<Posts> searchPosts(String keyword) {
        List<Posts> posts = festivalRepository.findByfestivalTitle(keyword);
        if(keyword.isEmpty()) return findFestivals();
        else {
            return posts;
        }
    }


    public int deleteBypostNum(Long postNum) {
        return festivalRepository.deleteBypostNum(postNum);
    }


    @Transactional
    public void updatePosts(Long postNum, Posts posts ) {
        Posts post = festivalRepository.findOne(postNum);
        post.setContentText(posts.getContentText());
        post.setFestivalTitle(posts.getFestivalTitle());
        post.setContentImage(posts.getContentImage());
        post.setFestivalUploadDate(posts.getFestivalUploadDate());
        post.setBoardAddr(posts.getBoardAddr());

    }


}