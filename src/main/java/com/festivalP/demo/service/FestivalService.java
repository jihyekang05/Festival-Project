package com.festivalP.demo.service;


import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FestivalService {

    private final FestivalRepository festivalRepository;

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

    public int deleteByPost_num(Long post_num) {
        return festivalRepository.deleteByPost_num(post_num);
    }
}