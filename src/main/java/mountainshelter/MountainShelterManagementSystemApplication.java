package mountainshelter;

import mountainshelter.model.Client;
import mountainshelter.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MountainShelterManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MountainShelterManagementSystemApplication.class, args);

    }

}
