package com.festivalP.demo.repository;

import com.festivalP.demo.domain.Member;
import com.festivalP.demo.domain.Posts;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Posts, Long> {



    @Override
    Page<Posts> findAll( Pageable pageable);


    Page<Posts> findByFestivalTitleContaining(String keyword, Pageable pageable);
}
