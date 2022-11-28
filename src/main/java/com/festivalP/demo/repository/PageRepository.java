package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PageRepository extends PagingAndSortingRepository<Posts, Long>{



    @Override
    Page<Posts> findAll( Pageable pageable);





    Page<Posts> findByFestivalTitleContaining(String keyword, Pageable pageable);



    //오래된 순
    Page<Posts> findAllByOrderByFestivalUploadDate(Pageable pageable);

    //최신 순
    Page<Posts> findAllByOrderByFestivalUploadDateDesc(Pageable pageable);

    //조회수 순
    Page<Posts> findAllByOrderByContentViewsDesc(Pageable pageable);

    @Modifying
    @Query("update Posts p set p.contentViews = p.contentViews + 1 where p.postNum =postNum")
    int updateView(Long postNum);



//    Page<Posts> findByFestivalTitleContaining(String keyword, Pageable pageable);

    Page<Posts> findByBoardLocAddrContaining(Long local, Pageable pageable);

//    @Modifying
//    @Query(value = "select p from Posts p JOIN FETCH p.favorite f on p.postNum = f.postNum and f.memberIndex = memberIndex")
//    Page<Posts> findByFestivalContent(Long memberIndex, Pageable pageable);
//
//    @Modifying
//    @Query(value = "select p from Posts p JOIN FETCH p.favorite f on p.postNum = f.postNum and f.memberIndex = memberIndex and p.festivalTitle like %title%")
//    Page<Posts> findByFestivalTitleContaining(Long memberIndex, String title, Pageable pageable);

}

