package com.festivalP.demo.service;

import com.festivalP.demo.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FestivalService {

    private final FestivalRepository festivalRepository;

    //전체목록 조회
    public List<Festival> findFestival() {
        return festivalRepository.findAll();
    }

    //즐겨찾기 조회

    //축제상세페이지 조회

}
