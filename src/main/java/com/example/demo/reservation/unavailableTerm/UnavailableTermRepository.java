package com.example.demo.reservation.unavailableTerm;

import com.example.demo.reservation.unavailableTerm.UnavailableTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailableTermRepository extends JpaRepository<UnavailableTerm, Long> {
}
