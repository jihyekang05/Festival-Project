package com.festivalP.demo.repository;

import com.festivalP.demo.domain.*;
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

    public void saveCategory(Member member) {
        System.out.println("MemberRepository.saveCategory");

        String[] categoryClassList = member.getMemberCategory().split(",");

        for(String s : categoryClassList){
            System.out.println("## categoryClassList :: "+ s);
            Category category = new Category();
            category.setMemberIndex(member.getMemberIndex());
            category.setCategoryClass(s);
            em.persist(category);
        }
    }

    public Member findOne(Long memberIndex) {

        return em.find(Member.class, memberIndex);
    }


    public List<Member> findById(String id) {

        return em.createQuery("select m from Member m where m.memberId = :id", Member.class).setParameter("id", id).getResultList();
    }


    public List<Member> findByNickname(String nickname){
        return em.createQuery("select m from Member m where m.memberNickname = :memberNickname", Member.class).setParameter("memberNickname", nickname).getResultList();
    }


    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m where m.memberEmail = :memberEmail", Member.class).setParameter("memberEmail", email).getResultList();
    }

    public Member findByEmailOne(String email){
        return em.createQuery("select m from Member m where m.memberEmail = :memberEmail", Member.class).setParameter("memberEmail", email).getSingleResult();
    }


    public Member memberInfoUpdate(Member member){

        Member resMember = em.find(Member.class, member.getMemberIndex());

        String[] categoryClassList = member.getMemberCategory().split(",");

        List<Category> categoryList = em.createQuery("select c from Category c where c.memberIndex= :memberIndex").setParameter("memberIndex", member.getMemberIndex()).getResultList();


        for(Category c : categoryList){
            em.remove(c);
        }

        for(String s : categoryClassList){
            Category category = new Category();

            category.setMemberIndex(member.getMemberIndex());
            category.setCategoryClass(s);
            em.persist(category);
        }



        resMember.setMemberNickname(member.getMemberNickname());
        resMember.setMemberBirth(member.getMemberBirth());
        resMember.setMemberEmail(member.getMemberEmail());
        resMember.setMemberAddr(member.getMemberAddr());
        resMember.setMemberCategory(member.getMemberCategory());

        System.out.println("#$#### memberInfoUpdate complete!!");
        return resMember;
    }

    public Member memberPasswordUpdate(Member member, String newPw){

        Member resMember = em.find(Member.class, member.getMemberIndex());

        resMember.setMemberPw(newPw);

        System.out.println("#$#### memberPasswordUpdate complete!!");
        return resMember;
    }



    public Member memberDelete(Member member){

        Member delMember = em.find(Member.class, member.getMemberIndex());

        delMember.setMemberState(1);
        return delMember;
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }


    public void memberStateUpdate(Long memberIndex, String newPw){

        Member resMem = em.find(Member.class, memberIndex);
        resMem.setMemberPw(newPw);
        System.out.println("## set memberPw to tempPw ##");
    }


}