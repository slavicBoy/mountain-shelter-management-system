package com.example.demo.repositories;

import com.example.demo.model.ReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDetailsRepository extends JpaRepository<ReservationDetails, Long> {
}
