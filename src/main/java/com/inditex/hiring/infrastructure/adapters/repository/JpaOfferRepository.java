package com.inditex.hiring.infrastructure.adapters.repository;


import com.inditex.hiring.infrastructure.adapters.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOfferRepository extends JpaRepository<OfferEntity, Long> {
}