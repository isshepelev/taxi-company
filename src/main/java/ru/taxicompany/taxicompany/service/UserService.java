package ru.taxicompany.taxicompany.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.taxicompany.taxicompany.domain.User;
import ru.taxicompany.taxicompany.domain.UsersCars;
import ru.taxicompany.taxicompany.dto.RegistrationUserDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);

    User createNewUser(RegistrationUserDTO registrationUserDto);

    Object rentCar(String name, Long carId);

    ResponseEntity<List<UsersCars>> getAllRentedCars();

    Object returnCar(String name, Long carId);

    List<User> getAllUsers();
}
