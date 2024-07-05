package com.inditex.hiring.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Offer {

    private Long offerId;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceListId;
    private String size; // Talla (dos primeros caracteres)
    private String model; // Modelo (siguientes cuatro caracteres)
    private String quality; // Calidad (tres últimos caracteres)
    private Integer priority;
    private BigDecimal price;
    private String currencyIso;

    public Offer() {
        // Constructor vacío requerido por frameworks y deserialización
    }

    public Offer(Long offerId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate,
                 Integer priceListId, String size, String model, String quality,
                 Integer priority, BigDecimal price, String currencyIso) {
        this.offerId = offerId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceListId = priceListId;
        this.size = size;
        this.model = model;
        this.quality = quality;
        this.priority = priority;
        this.price = price;
        this.currencyIso = currencyIso;
    }

    // Getters and setters

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyIso() {
        return currencyIso;
    }

    public void setCurrencyIso(String currencyIso) {
        this.currencyIso = currencyIso;
    }
}
