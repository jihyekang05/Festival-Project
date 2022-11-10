package com.festivalP.demo.service;


import com.festivalP.demo.domain.Member;
import com.festivalP.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    private Member encryptFunc(Member member){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(30);


        String pw = member.getMember_pw();
        String securePw = encoder.encode(pw);

        member.setMember_pw(securePw);
        return member;
    }



// 암호화 함수 사용 전
//    @Transactional
//    public String join(Member member){
//        System.out.println("MemberService.join");
//
//        memberRepository.save(member);
//        return member.getMember_id();
//    }

    @Transactional
    public String join(Member member){
        System.out.println("MemberService.join");

        memberRepository.save(encryptFunc(member));
        return member.getMember_id();
    }

    @Transactional
    public boolean validateDuplicateMemberId(String member_id){
        List<Member> findMem = memberRepository.findById(member_id);
        if(!findMem.isEmpty()) {
            // 중복된 ID 있을 경우
            System.out.println("@@@@@@@ duplicate! @@@@");
            return false;
        }
        else{
            // 중복된 ID 없을 경우
            System.out.println("$$$$$$$$ no id in db$$$$$$$$$$");
            return true;
        }
    }

    @Transactional
    public boolean validateDuplicateMemberNickname(String member_nickname){
        List<Member> findMem = memberRepository.findByNickname(member_nickname);
        if(!findMem.isEmpty()) {
            // 중복된 ID 있을 경우
            System.out.println("@@@@@@@ duplicate! @@@@");
            return false;
        }
        else{
            // 중복된 닉네임 없을 경우
            System.out.println("$$$$$$$$ no nickname in db$$$$$$$$$$");
            return true;
        }
    }
}

//    @Transactional
//    public String join(Member member){
//        System.out.println("MemberService.join");
//        // member 중복 검증
////        validateDuplicateMember(member);
//        memberRepository.save(member);
//        return member.getMember_id();
//    }


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
