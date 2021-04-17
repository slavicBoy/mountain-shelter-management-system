package com.example.demo.model.worker;
import com.example.demo.model.reservation.ReservationDetails;
import javax.persistence.*;
import java.util.List;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "worker")
    private List<ReservationDetails> reservationList;

    public Worker() {
    }

    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ReservationDetails> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationDetails> reservationList) {
        this.reservationList = reservationList;
    }
}
