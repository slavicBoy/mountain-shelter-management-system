package com.example.demo.model.room;

import javax.persistence.Column;
import java.math.BigDecimal;

public class RoomDto {

    private Long id;
    private String description;
    private Integer forHowManyPeople;
    private boolean isBathroom;
    private BigDecimal pricePerPeron;
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getForHowManyPeople() {
        return forHowManyPeople;
    }

    public void setForHowManyPeople(Integer forHowManyPeople) {
        this.forHowManyPeople = forHowManyPeople;
    }

    public boolean isBathroom() {
        return isBathroom;
    }

    public void setBathroom(boolean bathroom) {
        isBathroom = bathroom;
    }

    public BigDecimal getPricePerPeron() {
        return pricePerPeron;
    }

    public void setPricePerPeron(BigDecimal pricePerPeron) {
        this.pricePerPeron = pricePerPeron;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
