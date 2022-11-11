package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Member;
import lombok.RequiredArgsConstructor;
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

        return em.createQuery("select m from Member m where m.member_id = : id", Member.class).setParameter("id", id).getResultList();
    }

    public List<Member> findByNickname(String nickname){
        return em.createQuery("delete m from Member m where m.member_nickname = : nickname", Member.class).setParameter("nickname", nickname).getResultList();
    }

//    public Member findByIdAndPw(String id, String pw){
//
//        return em.createQuery("select m from Member m where m.member_id = :id and m.member_pw = :pw", Member.class).setParameter("id", id, "pw", pw).getFirstResult();
//    }






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