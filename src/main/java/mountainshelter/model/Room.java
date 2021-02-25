package mountainshelter.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "for_how_many_people", nullable = false)
    private int forHowManyPeople;




}
