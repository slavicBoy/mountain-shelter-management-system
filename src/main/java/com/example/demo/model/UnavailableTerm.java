package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "unavailable_term")
public class UnavailableTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_of_unavailable_term")
    private LocalDate startOfUnavailableTerm;
    @Column(name = "end_of_unavailable_term")
    private LocalDate endOfUnavailableTerm;


    public UnavailableTerm() {
    }

    public UnavailableTerm(LocalDate startOfUnavailableTerm, LocalDate endOfUnavailableTerm) {
        this.startOfUnavailableTerm = startOfUnavailableTerm;
        this.endOfUnavailableTerm = endOfUnavailableTerm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartOfUnavailableTerm() {
        return startOfUnavailableTerm;
    }

    public void setStartOfUnavailableTerm(LocalDate startOfUnavailableTerm) {
        this.startOfUnavailableTerm = startOfUnavailableTerm;
    }

    public LocalDate getEndOfUnavailableTerm() {
        return endOfUnavailableTerm;
    }

    public void setEndOfUnavailableTerm(LocalDate endOfUnavailableTerm) {
        this.endOfUnavailableTerm = endOfUnavailableTerm;
    }
}
