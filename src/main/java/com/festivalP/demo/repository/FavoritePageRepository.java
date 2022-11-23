package com.festivalP.demo.repository;


import com.festivalP.demo.domain.Favorite;
import com.festivalP.demo.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePageRepository extends PagingAndSortingRepository<Posts, Long> {

    @Override
    Page<Posts> findAll( Pageable pageable);


    @Query("select p from Posts p JOIN FETCH Favorite f on p.postNum = f.postNum where f.memberIndex = :memberIndex")
    Page<Posts> findByMemberIndex(@Param("memberIndex") Long memberIndex, Pageable pageable);

//    @Query("select p from Posts p JOIN FETCH Favorite f on p.postNum = f.postNum where f.memberIndex = :memberIndex and p.festivalTitle like %:keyword%")
//    Page<Posts> findByFestivalTitle(@Param("memberIndex") Long memberIndex, @Param("keyword") String keyword, Pageable pageable);

    @Query("select p from Posts p JOIN FETCH Favorite f on p.postNum = f.postNum where f.memberIndex = :memberIndex and p.festivalTitle like %:keyword%")
    Page<Posts> findByFestivalTitle(@Param("memberIndex") Long memberIndex, @Param("keyword") String keyword, Pageable pageable);
}


