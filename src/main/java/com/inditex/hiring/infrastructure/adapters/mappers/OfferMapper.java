package com.inditex.hiring.infrastructure.adapters.mappers;

import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.infrastructure.adapters.entity.OfferEntity;

import java.sql.Timestamp;

public class OfferMapper {

    public static Offer toDomain(OfferEntity entity) {
        return new Offer(
                entity.getOfferId(),
                entity.getBrandId(),
                entity.getStartDate().toLocalDateTime(),
                entity.getEndDate().toLocalDateTime(),
                entity.getPriceListId(),
                entity.getSize(),
                entity.getModel(),
                entity.getQuality(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrencyIso()
        );
    }

    public static OfferEntity toEntity(Offer domain) {
        OfferEntity entity = new OfferEntity();
        entity.setOfferId(domain.getOfferId());
        entity.setBrandId(domain.getBrandId());
        entity.setStartDate(Timestamp.valueOf(domain.getStartDate()));
        entity.setEndDate(Timestamp.valueOf(domain.getEndDate()));
        entity.setPriceListId(domain.getPriceListId());
        entity.setSize(domain.getSize());
        entity.setModel(domain.getModel());
        entity.setQuality(domain.getQuality());
        entity.setPriority(domain.getPriority());
        entity.setPrice(domain.getPrice());
        entity.setCurrencyIso(domain.getCurrencyIso());
        return entity;
    }

    public static String getProductPartnumber(Offer domain) {
        // Assuming productPartnumber is already formatted in the required way
        return domain.getSize()+domain.getModel()+domain.getQuality();
    }

    public static String[] splitProductPartnumber(String formattedProductPartnumber) {
        if (formattedProductPartnumber == null || formattedProductPartnumber.length() != 9) {
            throw new IllegalArgumentException("El número de parte debe tener 9 caracteres.");
        }

        // Dividir el número de parte en sus componentes
        String part1 = formattedProductPartnumber.substring(0, 2); // TT
        String part2 = formattedProductPartnumber.substring(2, 6); // MMMM
        String part3 = formattedProductPartnumber.substring(6, 9); // QQQ

        return new String[]{part1, part2, part3};
    }
}
