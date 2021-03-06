package com.example.demo.reservation;


public class ReservationMapper {

    public static ReservationDto toDto(Reservation reservation) {
        return ReservationDto.getBuilder()
                .id(reservation.getId())
                .firstName(reservation.getFirstName())
                .lastName(reservation.getLastName())
                .howManyPeople(reservation.getHowManyPeople())
                .reservationDayStart(reservation.getReservationDayStart())
                .reservationDayEnd(reservation.getReservationDayEnd())
                .phoneNumber(reservation.getPhoneNumber())
                .email(reservation.getDetails().getEmail())
                .extraInformation(reservation.getDetails().getExtraInformation())
                .reservationTime(reservation.getDetails().getDateOfAddingReservation())
                .wasDiscountShowed(reservation.getDetails().getWasDiscountShowed())
                .nights(reservation.getDetails().getNights())
                .amountToPay(reservation.getAmountToPay())
                .amountWithDiscount(reservation.getAmountWithDiscount())
                .isAdvancePaid(reservation.getDetails().getAdvancePaid())
                .isAccommodationPaid(reservation.getDetails().getAccommodationPaid())
                .roomNumber(reservation.getRoom().getRoomNumber())
                .placesLeft(reservation.getUnavailableTerm().getPlacesAvailable())
                .build();
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
