package com.inditex.hiring.domain.repository;

import com.inditex.hiring.domain.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferRepository {
    Optional<Offer> findById(Long offerId);
    List<Offer> findAll();
    Offer save(Offer offer);
    void deleteById(Long offerId);
    void deleteAll();
}