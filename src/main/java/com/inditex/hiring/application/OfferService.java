package com.inditex.hiring.application;

import com.inditex.hiring.application.mappers.OfferDtoMapper;
import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.inditex.hiring.application.dto.OfferDto;


@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public OfferDto getOfferById(Long offerId) {
        return offerRepository.findById(offerId)
                .map(OfferDtoMapper::toDto)
                .orElse(null);
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(OfferDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public OfferDto saveOffer(OfferDto offerDto) {
        Offer offer = OfferDtoMapper.toDomain(offerDto);
        Offer savedOffer = offerRepository.save(offer);
        return OfferDtoMapper.toDto(savedOffer);
    }

    public void deleteOffer(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    public void deleteAllOffers() {
        offerRepository.deleteAll();
    }
}
