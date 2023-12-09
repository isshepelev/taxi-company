package ru.taxicompany.taxicompany;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.domain.Role;
import ru.taxicompany.taxicompany.domain.User;
import ru.taxicompany.taxicompany.repository.CarRepository;
import ru.taxicompany.taxicompany.repository.RoleRepository;
import ru.taxicompany.taxicompany.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TaxiCompanyApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final RoleRepository roleRepository;


    public static void main(String[] args) {
        SpringApplication.run(TaxiCompanyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setName("ROLE_USER");
        role2.setName("ROLE_ADMIN");
        roleRepository.save(role1);
        roleRepository.save(role2);

        List<Car> cars =
        List.of(new Car(1L,"Toyota Corolla",20000,"Toyota", 2020,0,false),
                new Car(1L,"Tesla Model S",80000,"Tesla", 2022,0,false),
                new Car(1L,"BMW 5 Series",60000,"BMW", 2021,0,false),
                new Car(1L,"Honda Civic",25000,"Honda", 2023,0,false),
                new Car(1L,"Mercedes-Benz E-Class",70000,"Mercedes-Benz", 2022,0,false),
                new Car(1L,"Ford Mustang",45000,"Ford", 2023,0,false),
                new Car(1L,"Audi Q5",55000,"Audi", 2021,0,false),
                new Car(1L,"Chevrolet Camaro",50000,"Chevrolet", 2023,0,false),
                new Car(1L,"Subaru Outback",35000,"Subaru", 2022,0,false),
                new Car(1L,"Volkswagen Golf",30000,"Volkswagen", 2023,0,false));
        carRepository.saveAll(cars);

        List<Role> roleUser = List.of(role1);
        List<Role> roleAdmin = List.of(role2);
        List<User> users = List.of(
                new User("admin", "$2a$10$DWPvaWiyOkj1p4Fh8yttWOwLssK8ax7k69Swe1UD1rZ5/w9J9m87W", roleAdmin, null),
                new User("user1", "$2a$10$ee18PJTwkq7J7GhxzCoVhefndBLJCvldBrYIqtW2R4lYYhv4vjjbC", roleUser, null),
                new User("user2", "$2a$10$ee18PJTwkq7J7GhxzCoVhefndBLJCvldBrYIqtW2R4lYYhv4vjjbC", roleUser, null),
                new User("user3", "$2a$10$ee18PJTwkq7J7GhxzCoVhefndBLJCvldBrYIqtW2R4lYYhv4vjjbC", roleUser, null),
                new User("user4", "$2a$10$ee18PJTwkq7J7GhxzCoVhefndBLJCvldBrYIqtW2R4lYYhv4vjjbC", roleUser, null),
                new User("user5", "$2a$10$ee18PJTwkq7J7GhxzCoVhefndBLJCvldBrYIqtW2R4lYYhv4vjjbC", roleUser, null)
        );
        userRepository.saveAll(users);
    }
}