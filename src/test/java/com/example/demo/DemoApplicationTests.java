package com.example.demo;

import com.example.demo.unavailableTerm.UnavailableTerm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class DemoApplicationTests {
    UnavailableTerm unavailableTerm = new UnavailableTerm(LocalDate.of(2020, 3, 5),
            LocalDate.of(2020, 3, 15));
    boolean isTermFree = true;

    @Test
    public void you_should_reserve_room_when_term_is_free() {
        //given
        LocalDate startOfReservation = LocalDate.of(2020, 3, 20);
        LocalDate endOfReservation = LocalDate.of(2020, 3, 25);
        //when
        if (unavailableTerm.getStartOfUnavailableTerm().isAfter(startOfReservation)) {
            if (!endOfReservation.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isBefore(startOfReservation)) {
            if (!startOfReservation.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isEqual(startOfReservation)) {
            isTermFree = false;
        }
        //then
        assertThat(isTermFree, is(true));
    }

    @Test
    public void you_shouldnt_reserve_room_when_start_of_reservation_and_end_of_reservation_is_between_unavailable_term_start_and_unavailable_term_end() {
        //given
        LocalDate startOfReservation = LocalDate.of(2020, 3, 7);
        LocalDate endOfReservation = LocalDate.of(2020, 3, 10);
        //when
        if (unavailableTerm.getStartOfUnavailableTerm().isAfter(startOfReservation)) {
            if (!endOfReservation.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isBefore(startOfReservation)) {
            if (!startOfReservation.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isEqual(startOfReservation)) {
            isTermFree = false;
        }
        //then
        assertThat(isTermFree, is(false));
    }

    @Test
    public void you_shouldnt_reserve_room_when_start_of_reservation_is_before_unavailable_term_start_and_end_of_reservation_after_unavailable_term_start() {
        //given
        LocalDate startOfReservation = LocalDate.of(2020, 3, 3);
        LocalDate endOfReservation = LocalDate.of(2020, 3, 10);
        //when
        if (unavailableTerm.getStartOfUnavailableTerm().isAfter(startOfReservation)) {
            if (!endOfReservation.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isBefore(startOfReservation)) {
            if (!startOfReservation.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isEqual(startOfReservation)) {
            isTermFree = false;
        }
        //then
        assertThat(isTermFree, is(false));
    }

    @Test
    public void you_shouldnt_reserve_room_when_start_of_reservation_is_after_unavailable_term_start_and_before_end_of_unavailable_term_end() {
        //given
        LocalDate startOfReservation = LocalDate.of(2020, 3, 10);
        LocalDate endOfReservation = LocalDate.of(2020, 3, 15);
        //when
        if (unavailableTerm.getStartOfUnavailableTerm().isAfter(startOfReservation)) {
            if (!endOfReservation.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isBefore(startOfReservation)) {
            if (!startOfReservation.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                isTermFree = false;
            }
        } else if (unavailableTerm.getStartOfUnavailableTerm().isEqual(startOfReservation)) {
            isTermFree = false;
        }
        //then
        assertThat(isTermFree, is(false));
    }
     @Test
    public void you_shouldnt_reserve_when_start_of_reservation_equals_unavailable_term_end(){
         //given
         LocalDate startOfReservation = LocalDate.of(2020, 3, 15);
         LocalDate endOfReservation = LocalDate.of(2020, 3, 20);
         //when
         if (unavailableTerm.getStartOfUnavailableTerm().isAfter(startOfReservation)) {
             if (!endOfReservation.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                 isTermFree = false;
             }
         } else if (unavailableTerm.getStartOfUnavailableTerm().isBefore(startOfReservation)) {
             if (!startOfReservation.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                 isTermFree = false;
             }
         } else if (unavailableTerm.getStartOfUnavailableTerm().isEqual(startOfReservation)) {
             isTermFree = false;
         }
         //then
         assertThat(isTermFree, is(false));
     }


}
