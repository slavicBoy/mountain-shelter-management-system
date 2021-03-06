package com.example.demo.reservation.repository;

import com.example.demo.reservation.ReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDetailsRepository extends JpaRepository<ReservationDetails, Long> {
}
