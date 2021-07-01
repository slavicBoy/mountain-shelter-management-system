package com.example.demo.room;

import java.math.BigDecimal;

public class RoomDto {

    private Long id;
    private String description;
    private Integer forHowManyPeople;
    private boolean isBathroom;
    private BigDecimal pricePerPeron;
    private String imgUrl;
    private Integer roomNumber;

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

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", forHowManyPeople=" + forHowManyPeople +
                ", isBathroom=" + isBathroom +
                ", pricePerPeron=" + pricePerPeron +
                ", imgUrl='" + imgUrl + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
