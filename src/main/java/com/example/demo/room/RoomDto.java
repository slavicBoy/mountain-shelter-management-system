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
    private Integer placesLeft;




    private RoomDto() {

    }

    public static RoomDtoBuilder getBuilder() {
        return new RoomDtoBuilder();
 
    }

    static class RoomDtoBuilder {
        private RoomDto roomDto = new RoomDto();

        public RoomDtoBuilder id(Long id){
            roomDto.id = id;
            return this;
        }

        public RoomDtoBuilder description(String description){
            roomDto.description = description;
            return this;
        }

        public RoomDtoBuilder isBathroom(boolean isBathroom){
            roomDto.isBathroom = isBathroom;
            return this;
        }

        public RoomDtoBuilder forHowManyPeople(int howManyPeople){
            roomDto.forHowManyPeople = howManyPeople;
            return this;
        }

        public RoomDtoBuilder pricePerPerson(BigDecimal pricePerPerson){
            roomDto.pricePerPeron = pricePerPerson;
            return this;
        }

        public RoomDtoBuilder imgUrl(String imgUrl){
            roomDto.imgUrl = imgUrl;
            return this;
        }

        public RoomDtoBuilder roomNumber(int roomNumber){
            roomDto.roomNumber = roomNumber;
            return this;
        }

        public RoomDtoBuilder placesLeft(int placesLeft){
            roomDto.placesLeft = placesLeft;
            return this;
        }
        public RoomDto build() {
            return roomDto;
        }
    }

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

    public Integer getPlacesLeft() {
        return placesLeft;
    }

    public void setPlacesLeft(Integer placesLeft) {
        this.placesLeft = placesLeft;
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
                ", placesLeft=" + placesLeft +
                '}';
    }
}
