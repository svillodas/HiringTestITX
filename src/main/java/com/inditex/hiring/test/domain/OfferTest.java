package com.inditex.hiring.test.domain;

import com.inditex.hiring.domain.model.Offer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OfferTest {

    @Test
    public void testOfferConstructorAndGetters() {
        // Arrange
        Long offerId = 1L;
        Integer brandId = 123;
        LocalDateTime startDate = LocalDateTime.of(2024, 7, 5, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 23, 59);
        Integer priceListId = 456;
        String size = "XS";
        String model = "ABCD";
        String quality = "High";
        Integer priority = 1;
        BigDecimal price = new BigDecimal("99.99");
        String currencyIso = "USD";

        // Act
        Offer offer = new Offer(offerId, brandId, startDate, endDate, priceListId,
                size, model, quality, priority, price, currencyIso);

        // Assert
        assertNotNull(offer);
        assertEquals(offerId, offer.getOfferId());
        assertEquals(brandId, offer.getBrandId());
        assertEquals(startDate, offer.getStartDate());
        assertEquals(endDate, offer.getEndDate());
        assertEquals(priceListId, offer.getPriceListId());
        assertEquals(size, offer.getSize());
        assertEquals(model, offer.getModel());
        assertEquals(quality, offer.getQuality());
        assertEquals(priority, offer.getPriority());
        assertEquals(price, offer.getPrice());
        assertEquals(currencyIso, offer.getCurrencyIso());
    }

    @Test
    public void testOfferSetters() {
        // Arrange
        Offer offer = new Offer();

        Long offerId = 1L;
        Integer brandId = 123;
        LocalDateTime startDate = LocalDateTime.of(2024, 7, 5, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 23, 59);
        Integer priceListId = 456;
        String size = "XS";
        String model = "ABCD";
        String quality = "High";
        Integer priority = 1;
        BigDecimal price = new BigDecimal("99.99");
        String currencyIso = "USD";

        // Act
        offer.setOfferId(offerId);
        offer.setBrandId(brandId);
        offer.setStartDate(startDate);
        offer.setEndDate(endDate);
        offer.setPriceListId(priceListId);
        offer.setSize(size);
        offer.setModel(model);
        offer.setQuality(quality);
        offer.setPriority(priority);
        offer.setPrice(price);
        offer.setCurrencyIso(currencyIso);

        // Assert
        assertEquals(offerId, offer.getOfferId());
        assertEquals(brandId, offer.getBrandId());
        assertEquals(startDate, offer.getStartDate());
        assertEquals(endDate, offer.getEndDate());
        assertEquals(priceListId, offer.getPriceListId());
        assertEquals(size, offer.getSize());
        assertEquals(model, offer.getModel());
        assertEquals(quality, offer.getQuality());
        assertEquals(priority, offer.getPriority());
        assertEquals(price, offer.getPrice());
        assertEquals(currencyIso, offer.getCurrencyIso());
    }
}
