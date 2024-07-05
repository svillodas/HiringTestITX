package com.inditex.hiring.test.infrastructure.adapters.repository;

import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OfferRepositoryTest {

    @Mock
    private OfferRepository offerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        Long offerId = 1L;
        Offer offer = new Offer();
        offer.setOfferId(offerId);
        offer.setStartDate(LocalDateTime.now());
        offer.setEndDate(LocalDateTime.now().plusMonths(1));
        offer.setPriceListId(10);
        offer.setPrice(new BigDecimal("99.99"));
        offer.setCurrencyIso("USD");
        offer.setBrandId(1);
        offer.setSize("22");
        offer.setModel("4556");
        offer.setQuality("333");
        offer.setPriority(1);

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(offer));

        // Act
        Optional<Offer> retrievedOffer = offerRepository.findById(offerId);

        // Assert
        assertTrue(retrievedOffer.isPresent());
        assertEquals(offerId, retrievedOffer.get().getOfferId());
        verify(offerRepository, times(1)).findById(offerId);
    }

    @Test
    public void testSave() {
        // Arrange
        Offer offer = new Offer();
        offer.setOfferId(1L);
        offer.setStartDate(LocalDateTime.now());
        offer.setEndDate(LocalDateTime.now().plusMonths(1));
        offer.setPriceListId(10);
        offer.setPrice(new BigDecimal("99.99"));
        offer.setCurrencyIso("USD");
        offer.setBrandId(1);
        offer.setSize("22");
        offer.setModel("4556");
        offer.setQuality("333");
        offer.setPriority(1);

        when(offerRepository.save(any(Offer.class))).thenReturn(offer);

        // Act
        Offer savedOffer = offerRepository.save(offer);

        // Assert
        assertEquals(1L, savedOffer.getOfferId().longValue());
        verify(offerRepository, times(1)).save(offer);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Offer offer1 = new Offer();
        offer1.setOfferId(1L);
        offer1.setStartDate(LocalDateTime.now());
        offer1.setEndDate(LocalDateTime.now().plusMonths(1));
        offer1.setPriceListId(10);
        offer1.setPrice(new BigDecimal("99.99"));
        offer1.setCurrencyIso("USD");
        offer1.setBrandId(1);
        offer1.setSize("22");
        offer1.setModel("4556");
        offer1.setQuality("333");
        offer1.setPriority(1);

        Offer offer2 = new Offer();
        offer2.setOfferId(2L);
        offer2.setStartDate(LocalDateTime.now());
        offer2.setEndDate(LocalDateTime.now().plusMonths(1));
        offer2.setPriceListId(20);
        offer2.setPrice(new BigDecimal("129.99"));
        offer2.setCurrencyIso("EUR");
        offer2.setBrandId(2);
        offer1.setSize("72");
        offer1.setModel("4856");
        offer1.setQuality("313");
        offer2.setPriority(2);

        List<Offer> offers = Arrays.asList(offer1, offer2);

        when(offerRepository.findAll()).thenReturn(offers);

        // Act
        List<Offer> retrievedOffers = offerRepository.findAll();

        // Assert
        assertEquals(2, retrievedOffers.size());
        verify(offerRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long offerId = 1L;

        // Act
        offerRepository.deleteById(offerId);

        // Assert
        verify(offerRepository, times(1)).deleteById(offerId);
    }

    @Test
    public void testDeleteAll() {
        // Act
        offerRepository.deleteAll();

        // Assert
        verify(offerRepository, times(1)).deleteAll();
    }
}
