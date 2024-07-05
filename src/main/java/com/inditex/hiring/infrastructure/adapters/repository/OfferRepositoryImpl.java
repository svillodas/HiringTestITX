package com.inditex.hiring.infrastructure.adapters.repository;

import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.infrastructure.adapters.entity.OfferEntity;
import com.inditex.hiring.infrastructure.adapters.mappers.OfferMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OfferRepositoryImpl implements OfferRepository {

    private final JpaOfferRepository jpaOfferRepository;

    public OfferRepositoryImpl(JpaOfferRepository jpaOfferRepository) {
        this.jpaOfferRepository = jpaOfferRepository;
    }

    @Override
    public Optional<Offer> findById(Long offerId) {
        OfferEntity entity = jpaOfferRepository.findOne(offerId);
        return Optional.ofNullable(entity).map(OfferMapper::toDomain);
    }


    @Override
    public List<Offer> findAll() {
        return jpaOfferRepository.findAll().stream()
                .map(OfferMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Offer save(Offer offer) {
        OfferEntity entity = OfferMapper.toEntity(offer);
        return OfferMapper.toDomain(jpaOfferRepository.save(entity));
    }

    @Override
    public void deleteById(Long offerId) {
        jpaOfferRepository.delete(offerId);
    }

    @Override
    public void deleteAll() {
        jpaOfferRepository.deleteAll();
    }
}
