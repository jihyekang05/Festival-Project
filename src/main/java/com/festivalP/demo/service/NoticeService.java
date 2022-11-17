package com.festivalP.demo.service;


import com.festivalP.demo.domain.Notice;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.repository.MemberRepository;
import com.festivalP.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long join(Notice notice) {

        noticeRepository.save(notice);
        return notice.getPost_num();
    }

    public List<Notice> findNotice() {
        return noticeRepository.findAll();
    }

    @Transactional
    public int deleteByNotice_num(Long post_num) {
        return noticeRepository.deleteByNotice_num(post_num);
    }
}
