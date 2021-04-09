package com.example.demo.model.reservation;


public class ReservationMapper {

    public static ReservationDto toDto(Reservation reservation){
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());
        dto.setFirstName(reservation.getFirstName());
        dto.setLastName(reservation.getLastName());
        dto.setHowManyPeople(reservation.getHowManyPeople());
        dto.setReservationStart(reservation.getReservationDayStart());
        dto.setReservationEnd(reservation.getReservationDayEnd());
        dto.setPhoneNumber(reservation.getPhoneNumber());
        dto.setEmail(reservation.getDetails().getEmail());
        dto.setExtraInformation(reservation.getDetails().getExtraInformation());
        dto.setReservationTime(reservation.getDetails().getDateOfAddingReservation());
        return dto;
    }



}
