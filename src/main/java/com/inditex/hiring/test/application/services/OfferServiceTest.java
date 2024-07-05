package com.inditex.hiring.test.application.services;

import com.inditex.hiring.application.OfferService;
import com.inditex.hiring.application.dto.OfferDto;
import com.inditex.hiring.application.mappers.OfferDtoMapper;
import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferService offerService;


    @Test
    public void getOfferById_ReturnsOfferDto_WhenOfferExists() {
        Offer offer1 = new Offer();
        offer1.setOfferId(1L);
        offer1.setStartDate(LocalDateTime.now());
        offer1.setEndDate(LocalDateTime.now());
        offer1.setPriceListId(10);

        Mockito.when(offerRepository.findById(1L)).thenReturn(Optional.of(offer1));
        OfferDto offerDto = offerService.getOfferById(1L);

        assertNotNull(offerDto);
        assertEquals(1, (long) offerDto.getOfferId());
    }

    @Test
    public void getAllOffers_ReturnsEmptyList_WhenNoOffersExist() {
        Mockito.when(offerRepository.findAll()).thenReturn(Collections.emptyList());
        List<OfferDto> offers = offerService.getAllOffers();

        assertNotNull(offers);
        assertEquals(0, offers.size());
    }


    @Test
    public void getOfferById_ReturnsNull_WhenOfferDoesNotExist() {
        Mockito.when(offerRepository.findById(1L)).thenReturn(Optional.empty());
        OfferDto offerDto = offerService.getOfferById(1L);

        assertNull(offerDto);
    }

    @Test
   public void getAllOffers_ReturnsListOfOfferDtos() {
        Offer offer1 = new Offer();
        offer1.setOfferId(1L);
        offer1.setStartDate(LocalDateTime.now());
        offer1.setEndDate(LocalDateTime.now());
        offer1.setPriceListId(10);
        Offer offer2 = new Offer();
        offer2.setOfferId(2L);
        offer2.setStartDate(LocalDateTime.now());
        offer2.setEndDate(LocalDateTime.now());
        offer2.setPriceListId(10);

        OfferDto offerDto1 = new OfferDto();
        offerDto1.setOfferId(1L);
        OfferDto offerDto2 = new OfferDto();
        offerDto2.setOfferId(2L);

        Mockito.when(offerRepository.findAll()).thenReturn(Arrays.asList(offer1, offer2));
        List<OfferDto> offers = offerService.getAllOffers();

        assertEquals(2, offers.size());
        assertEquals(Long.valueOf(1L), offers.get(0).getOfferId());
        assertEquals(Long.valueOf(2L), offers.get(1).getOfferId());
    }

    @Test
    public void saveOffer_SavesAllFieldsCorrectly() {
        OfferDto offerDto = new OfferDto();
        offerDto.setOfferId(1L);
        offerDto.setBrandId(123);
        offerDto.setStartDate("2024-07-05 00:00:00");
        offerDto.setEndDate("2024-12-31 00:00:00");
        offerDto.setPriceListId(456L);
        offerDto.setProductPartnumber("002000265");
        offerDto.setPriority(1);
        offerDto.setPrice(new BigDecimal("99.99"));
        offerDto.setCurrencyIso("USD");

        Mockito.when(offerRepository.save(Matchers.any())).thenReturn(OfferDtoMapper.toDomain(offerDto));

        OfferDto savedOfferDto = offerService.saveOffer(offerDto);

        assertNotNull(savedOfferDto);
        assertEquals(1L, (long)savedOfferDto.getOfferId());
        assertEquals(123, (long) savedOfferDto.getBrandId());
        assertEquals("2024-07-05 00:00:00", savedOfferDto.getStartDate());
        assertEquals("2024-12-31 00:00:00", savedOfferDto.getEndDate());
        assertEquals(456L, savedOfferDto.getPriceListId().longValue());
        assertEquals("002000265", savedOfferDto.getProductPartnumber());
        assertEquals(1, (long) savedOfferDto.getPriority());
        assertEquals(new BigDecimal("99.99"), savedOfferDto.getPrice());
        assertEquals("USD", savedOfferDto.getCurrencyIso());
    }


    @Test
    public void deleteOffer_DeletesOffer() {
        Mockito.doNothing().when(offerRepository).deleteById(1L);
        offerService.deleteOffer(1L);
        Mockito.verify(offerRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllOffers_DeletesAllOffers() {
        Mockito.doNothing().when(offerRepository).deleteAll();
        offerService.deleteAllOffers();
        Mockito.verify(offerRepository, Mockito.times(1)).deleteAll();
    }
}
