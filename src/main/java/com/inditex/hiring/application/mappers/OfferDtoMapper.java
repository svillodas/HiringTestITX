package com.inditex.hiring.application.mappers;

import com.inditex.hiring.application.dto.OfferDto;
import com.inditex.hiring.domain.model.Offer;
import com.inditex.hiring.infrastructure.adapters.mappers.OfferMapper;
import static com.inditex.hiring.application.utils.DateParser.parseStringToLocalDateTime;

public class OfferDtoMapper {

    public static Offer toDomain(OfferDto dto) {
        String[] productPartNumber;
        productPartNumber = OfferMapper.splitProductPartnumber(dto.getProductPartnumber());

        return new Offer(
                dto.getOfferId(),
                dto.getBrandId(),
                parseStringToLocalDateTime(dto.getStartDate()),
                parseStringToLocalDateTime(dto.getEndDate()),
                Math.toIntExact(dto.getPriceListId()),
                productPartNumber[0],
                productPartNumber[1],
                productPartNumber[2],
                dto.getPriority(),
                dto.getPrice(),
                dto.getCurrencyIso()
        );
    }

    public static OfferDto toDto(Offer domain) {
        return new OfferDto(
                domain.getOfferId(),
                domain.getBrandId(),
                domain.getStartDate().toString(),
                domain.getEndDate().toString(),
                (long) domain.getPriceListId(),
                OfferMapper.getProductPartnumber(domain),
                domain.getPriority(),
                domain.getPrice(),
                domain.getCurrencyIso()
        );
    }


}
