package com.festivalP.demo.service;


import com.festivalP.demo.domain.Notice;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.repository.MemberRepository;
import com.festivalP.demo.repository.NoticeRepository;
import com.festivalP.demo.repository.PageNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final PageNoticeRepository pageNoticeRepository;

    @Transactional
    public Long join(Notice notice) {

        noticeRepository.save(notice);
        return notice.getPost_num();
    }

    public List<Notice> findNotice() {
        return noticeRepository.findAll();
    }

    public Page<Notice> paging(Pageable pageable) {
        // Page<Posts> Pages= pageRepository.findAll(pageable);
        return pageNoticeRepository.findAll(pageable);
    }


    @Transactional
    public int deleteByNotice_num(Long post_num) {
        return noticeRepository.deleteByNotice_num(post_num);
    }

    public List<Notice> findOne(Long post_num) {
        return noticeRepository.findByPost_num(post_num);
    }

    //검색
    @Transactional
    public List<Notice> searchNotice(String keyword) {
        List<Notice> notices = noticeRepository.findByNotice_Title(keyword);
        if(keyword.isEmpty()) return findNotice();
        else {
            return notices;
        }

    }


}
