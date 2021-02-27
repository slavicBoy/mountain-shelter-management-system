package mountainshelter.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
   @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "price", nullable = false)
    private BigDecimal priceByOneNight;
    @Column(name = "for_how_many_people", nullable = false)
    private int forHowManyPeople;
    @ManyToMany(mappedBy = "rooms")
    private List<Client> clients;
    @ManyToMany
    @JoinTable(name = "unavailable_terms", joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "data_id", referencedColumnName = "id")})
    private List<Date> dates;

    public Room() {
    }

    public Room(BigDecimal price, int forHowManyPeople) {
      this.priceByOneNight = price;
      this.forHowManyPeople = forHowManyPeople;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public BigDecimal getPrice() {
      return priceByOneNight;
   }

   public void setPrice(BigDecimal price) {
      this.priceByOneNight = price;
   }

   public int getForHowManyPeople() {
      return forHowManyPeople;
   }

   public void setForHowManyPeople(int forHowManyPeople) {
      this.forHowManyPeople = forHowManyPeople;
   }

   public List<Client> getClients() {
      return clients;
   }

   public void setClients(List<Client> clients) {
      this.clients = clients;
   }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public void addClient(Client client){
        if(clients == null){
            clients = new ArrayList<>();
        }
       clients.add(client);
   }
    public void addDate(Date date){
        if(dates == null){
            dates = new ArrayList<>();
        }
        dates.add(date);
    }
    @Override
    public String toString() {
        return "Room{" +
                "price=" + priceByOneNight +
                ", forHowManyPeople=" + forHowManyPeople +
                ", clients=" + clients +
                '}';
    }
}
