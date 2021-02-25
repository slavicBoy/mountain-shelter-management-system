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
    private BigDecimal price;
    @Column(name = "for_how_many_people", nullable = false)
    private int forHowManyPeople;
    @ManyToMany(mappedBy = "rooms")
    private List<Client> clients;

    public Room() {
    }

    public Room(BigDecimal price, int forHowManyPeople) {
      this.price = price;
      this.forHowManyPeople = forHowManyPeople;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
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

   public void addClient(Client client){
        if(clients == null){
            clients = new ArrayList<>();
        }
       clients.add(client);
   }

    @Override
    public String toString() {
        return "Room{" +
                "price=" + price +
                ", forHowManyPeople=" + forHowManyPeople +
                ", clients=" + clients +
                '}';
    }
}
