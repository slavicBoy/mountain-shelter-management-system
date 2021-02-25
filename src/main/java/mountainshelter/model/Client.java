package mountainshelter.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false, length = 15)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    @Column(name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;
    @Column(name = "companion_number")
    private Integer companionNumber;
    @Column(name = "reservation_day")
    private LocalDate localDate;
    @ManyToMany
    @JoinTable(name = "reservation", joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "id")})
    private List<Room> rooms;

    public Client() {
    }

    public Client(String firstName, String lastName, String phoneNumber, Integer companionNumber, LocalDate localDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.companionNumber = companionNumber;
        this.localDate = localDate;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCompanionNumber() {
        return companionNumber;
    }

    public void setCompanionNumber(Integer companionNumber) {
        this.companionNumber = companionNumber;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        rooms.add(room);
    }
}
