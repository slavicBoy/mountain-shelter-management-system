package com.example.demo.repositories;

import com.example.demo.model.unavailableTerm.UnavailableTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailableTermRepository extends JpaRepository<UnavailableTerm, Long> {
}
