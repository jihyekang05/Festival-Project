package com.festivalP.demo.repository;

import com.festivalP.demo.domain.festival;

import javax.persistence.EntityManager;

public class FestivalRepository {

    private final EntityManager em;

    public List<Festival> findAll() {
        return em.createQuery("select f from festival f", festival.class).getResultList();
    }
}
