package com.example.demo.reservation;


public class ReservationMapper {

    public static ReservationDto toDto(Reservation reservation) {
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
        dto.setAmountToPay(reservation.getAmountToPay());
        dto.setWasDiscountShowed(reservation.getDetails().getWasDiscountShowed());
        dto.setNights(reservation.getDetails().getNights());
        dto.setAmountToPay(reservation.getAmountToPay());
        dto.setAmountWithDiscount(reservation.getAmountWithDiscount());
        dto.setAdvancePaid(reservation.getDetails().getAdvancePaid());
        dto.setAccommodationPaid(reservation.getDetails().getAccommodationPaid());
        dto.setRoomNumber(reservation.getRoom().getRoomNumber());
        dto.setPlacesLeft(reservation.getUnavailableTerm().getPlacesAvailable());
        return dto;
    }

    public static Reservation toEntity(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setFirstName(reservationDto.getFirstName());
        reservation.setLastName(reservationDto.getLastName());
        reservation.setHowManyPeople(reservationDto.getHowManyPeople());
        reservation.setReservationDayStart(reservationDto.getReservationStart());
        reservation.setReservationDayEnd(reservationDto.getReservationEnd());
        reservation.setPhoneNumber(reservationDto.getPhoneNumber());
        reservation.setAmountToPay(reservationDto.getAmountToPay());
        reservation.setAmountWithDiscount(reservationDto.getAmountWithDiscount());
        reservation.setDetails(new ReservationDetails());
        reservation.getDetails().setEmail(reservationDto.getEmail());
        reservation.getDetails().setExtraInformation(reservationDto.getExtraInformation());
        reservation.getDetails().setNights(reservationDto.getNights());
        return reservation;
    }
}
