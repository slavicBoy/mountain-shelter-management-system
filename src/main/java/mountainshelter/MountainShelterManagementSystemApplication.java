package mountainshelter;

import mountainshelter.model.Client;
import mountainshelter.model.Room;
import mountainshelter.repository.ClientRepository;
import mountainshelter.repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class MountainShelterManagementSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MountainShelterManagementSystemApplication.class, args);


    }

}
