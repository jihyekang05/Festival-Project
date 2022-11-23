package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor

// 롬복 사용하여 생성자 의존성 주입
public class MemberRepository {

    private final EntityManager em;


    public void save(Member member) {
        // JPA에서 save 함수는 매개변수를 insert 해주는 동작
        System.out.println("MemberRepository.save");
        em.persist(member);
    }

    public List<Member> findById(String id) {

        return em.createQuery("select m from Member m where m.memberId = :id", Member.class).setParameter("id", id).getResultList();
    }


    public List<Member> findByNickname(String nickname){
        return em.createQuery("select m from Member m where m.memberNickname = :memberNickname", Member.class).setParameter("memberNickname", nickname).getResultList();
    }

    public List<Member> findByIdAndPw(String id, String pw){
        return em.createQuery("select m from Member m where m.memberId = :id and m.memberPw = :pw", Member.class)
                .setParameter("id", id)
                .setParameter("pw", pw).getResultList();
    }

    public Member memberInfoUpdate(Member member){

        Member resMember = em.find(Member.class, member.getMemberIndex());

        resMember.setMemberNickname(member.getMemberNickname());
        resMember.setMemberBirth(member.getMemberBirth());
        resMember.setMemberEmail(member.getMemberEmail());
        resMember.setMemberAddr(member.getMemberAddr());
        resMember.setMemberCategory(member.getMemberCategory());

        System.out.println("#$#### memberInfoUpdate complete!!");
        return resMember;
    }


    public Member memberDelete(Member member){

        Member delMember = em.find(Member.class, member.getMemberIndex());

        delMember.setMemberState(1);
        return delMember;
    }


//    public void save(Member member) {
//        em.persist(member);
//    }
//
//    public Member findOne(Long id) {
//        return em.find(Member.class, id);
//    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

//    public List<Member> findByName(String name) {
//        return em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//    }



}