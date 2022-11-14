package com.festivalP.demo.service;


import com.festivalP.demo.domain.Member;
import com.festivalP.demo.form.AuthInfo;
import com.festivalP.demo.form.MemberForm;
import com.festivalP.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Transactional
    private Member encryptFunc(Member member){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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

    // 암호화 함수 사용 전
//    @Transactional
//    public String join(Member member){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        member.setMember_pw(encoder.encode(member.getMember_pw()));
//        System.out.println("MemberService.join");
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

    @Transactional
    public boolean memberExistCheck(String member_id, String member_pw){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Member> findMem =memberRepository.findById(member_id);
        if(findMem.isEmpty()){
            return false;
        }
        else{
            return encoder.matches(member_pw,findMem.get(0).getMember_pw());
        }
    }


    @Transactional
    public AuthInfo getMemberAuthInfo(String member_id){
        List<Member> findMem =memberRepository.findById(member_id);
        Member mem = findMem.get(0);
        AuthInfo authInfo = new AuthInfo();
        authInfo.setId(member_id);
        authInfo.setEmail(mem.getMember_email());
        authInfo.setNickname(mem.getMember_nickname());
        authInfo.setState(mem.getMember_state());

        return authInfo;
    }

    @Transactional
    public Member getMemberAllInfo(String member_id){
        List<Member> findMem =memberRepository.findById(member_id);
        Member mem = findMem.get(0);
        return mem;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
