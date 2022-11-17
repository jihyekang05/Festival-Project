package com.festivalP.demo.repository;

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

@Repository
public interface PageRepository extends PagingAndSortingRepository<Posts, Long> {

//    @Override
//    Page<Posts> findAll(Posts posts, Pageable pageable);


//    @Override
//    Page<Posts> findAll(Pageable pageable);

//    @Override
//    List<Posts> findAll(Sort sort);

    @Override
    Page<Posts> findAll( Pageable pageable);

//    @Override
//    Page<Posts> findAllById(Iterable<Long> longs, Pageable pageable );
}
