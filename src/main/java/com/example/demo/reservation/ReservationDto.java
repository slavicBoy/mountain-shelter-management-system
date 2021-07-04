package com.example.demo.reservation;

import com.example.demo.room.RoomDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservationDto {

    private Long id;
    private int howManyPeople;
    private String firstName;
    private String LastName;
    private String phoneNumber;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private String email;
    private String extraInformation;
    private LocalDate reservationTime;
    private BigDecimal amountToPay;
    private boolean wasDiscountShowed;
    private Integer nights;
    private boolean isAdvancePaid;
    private boolean isAccommodationPaid;
    private BigDecimal amountWithDiscount;
    private int roomNumber;
    private int placesLeft;


    private ReservationDto() {
    }

    public static ReservationDtoBuilder getBuilder() {
        return new ReservationDtoBuilder();
    }

    static class ReservationDtoBuilder {

        private ReservationDto reservationDto = new ReservationDto();

        public ReservationDtoBuilder id(Long id) {
            reservationDto.id = id;
            return this;
        }

        public ReservationDtoBuilder howManyPeople(int howManyPeople) {
            reservationDto.howManyPeople = howManyPeople;
            return this;
        }

        public ReservationDtoBuilder firstName(String firstName) {
            reservationDto.firstName = firstName;
            return this;
        }

        public ReservationDtoBuilder lastName(String lastName) {
            reservationDto.LastName = lastName;
            return this;
        }

        public ReservationDtoBuilder phoneNumber(String phoneNumber) {
            reservationDto.phoneNumber = phoneNumber;
            return this;
        }

        public ReservationDtoBuilder reservationDayStart(LocalDate reservationDayStart) {
            reservationDto.reservationStart = reservationDayStart;
            return this;
        }

        public ReservationDtoBuilder reservationDayEnd(LocalDate reservationDayEnd) {
            reservationDto.reservationEnd = reservationDayEnd;
            return this;
        }

        public ReservationDtoBuilder email(String email) {
            reservationDto.email = email;
            return this;
        }

        public ReservationDtoBuilder extraInformation(String extraInformation) {
            reservationDto.extraInformation = extraInformation;
            return this;
        }

        public ReservationDtoBuilder reservationTime(LocalDate reservationTime) {
            reservationDto.reservationTime = reservationTime;
            return this;
        }

        public ReservationDtoBuilder amountToPay(BigDecimal amountToPay) {
            reservationDto.amountToPay = amountToPay;
            return this;
        }

        public ReservationDtoBuilder wasDiscountShowed(boolean wasDiscountShowed) {
            reservationDto.wasDiscountShowed = wasDiscountShowed;
            return this;
        }

        public ReservationDtoBuilder isAdvancePaid(boolean isAdvancePaid) {
            reservationDto.isAdvancePaid = isAdvancePaid;
            return this;
        }

        public ReservationDtoBuilder isAccommodationPaid(boolean isAccommodationPaid) {
            reservationDto.isAccommodationPaid = isAccommodationPaid;
            return this;
        }

        public ReservationDtoBuilder amountWithDiscount(BigDecimal amountWithDiscount) {
            reservationDto.amountWithDiscount = amountWithDiscount;
            return this;
        }

        public ReservationDtoBuilder roomNumber(int roomNumber) {
            reservationDto.roomNumber = roomNumber;
            return this;
        }

        public ReservationDtoBuilder placesLeft(int placesLeft) {
            reservationDto.placesLeft = placesLeft;
            return this;
        }

        public ReservationDtoBuilder nights(int nights) {
            reservationDto.nights = nights;
            return this;
        }

        public ReservationDto build() {
            return reservationDto;
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHowManyPeople() {
        return howManyPeople;
    }

    public void setHowManyPeople(int howManyPeople) {
        this.howManyPeople = howManyPeople;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(LocalDate reservationStart) {
        this.reservationStart = reservationStart;
    }

    public LocalDate getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(LocalDate reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public LocalDate getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDate reservationTime) {
        this.reservationTime = reservationTime;
    }


    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
    }

    public boolean wasDiscountShowed() {
        return wasDiscountShowed;
    }

    public void setWasDiscountShowed(boolean wasDiscountShowed) {
        this.wasDiscountShowed = wasDiscountShowed;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public boolean isAdvancePaid() {
        return isAdvancePaid;
    }

    public void setAdvancePaid(boolean advancePaid) {
        isAdvancePaid = advancePaid;
    }

    public boolean isAccommodationPaid() {
        return isAccommodationPaid;
    }

    public void setAccommodationPaid(boolean accommodationPaid) {
        isAccommodationPaid = accommodationPaid;
    }

    public BigDecimal getAmountWithDiscount() {
        return amountWithDiscount;
    }

    public void setAmountWithDiscount(BigDecimal amountWithDiscount) {
        this.amountWithDiscount = amountWithDiscount;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPlacesLeft() {
        return placesLeft;
    }

    public void setPlacesLeft(int placesLeft) {
        this.placesLeft = placesLeft;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", howManyPeople=" + howManyPeople +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reservationStart=" + reservationStart +
                ", reservationEnd=" + reservationEnd +
                ", email='" + email + '\'' +
                ", extraInformation='" + extraInformation + '\'' +
                ", reservationTime=" + reservationTime +
                ", amountToPay=" + amountToPay +
                ", wasDiscountShowed=" + wasDiscountShowed +
                ", nights=" + nights +
                ", isAdvancePaid=" + isAdvancePaid +
                ", isAccommodationPaid=" + isAccommodationPaid +
                ", amountWithDiscount=" + amountWithDiscount +
                ", roomNumber=" + roomNumber +
                ", placesLeft=" + placesLeft +
                '}';
    }
}
