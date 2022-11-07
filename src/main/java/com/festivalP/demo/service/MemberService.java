package com.festivalP.demo.service;


import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import com.festivalP.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    @Transactional
//    public String join(Member member){
//        System.out.println("MemberService.join");
//        // member 중복 검증
////        validateDuplicateMember(member);
//        memberRepository.save(member);
//        return member.getMember_id();
//    }

    public List<Member> findMember() {
        return memberRepository.findAll();
    }
//    private void validateDuplicateMember(Member member){
//        List<Member> findMem = memberRepository.findById(member.getMember_id());
//        if(!findMem.isEmpty()){
//            // 중복된 아이디가 있을 경우 동작
//            throw new IllegalStateException();
//        }
//        else{
//            // 죽복된 아이디가 없을 경우 동작
//            System.out.println("회원가입 성공~");
//
//        }
//    }
}