package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.domain.User;
import ru.taxicompany.taxicompany.domain.UsersCars;
import ru.taxicompany.taxicompany.dto.RegistrationUserDTO;
import ru.taxicompany.taxicompany.exception.AppError;
import ru.taxicompany.taxicompany.repository.UserRepository;
import ru.taxicompany.taxicompany.service.CarService;
import ru.taxicompany.taxicompany.service.RoleService;
import ru.taxicompany.taxicompany.service.UserService;
import ru.taxicompany.taxicompany.service.UsersCarsService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CarService carService;
    private final UsersCarsService usersCarsService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createNewUser(RegistrationUserDTO registrationUserDto) {
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }

    @Override
    public Object rentCar(String name, Long carId) {
        Optional<User> userOptional = findByUsername(name);
        Optional<Car> carOptional = carService.findByCarId(carId);
        if (carOptional.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машина с таким id не найдена"), HttpStatus.BAD_REQUEST);
        }

        Car car = carOptional.get();
        User user = userOptional.get();

        if (car.isAvailable()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машина с таким id уже занята"), HttpStatus.BAD_REQUEST);
        }

        user.getCars().add(car);
        userRepository.save(user);

        UsersCars usersCars = new UsersCars();
        usersCars.setCar(car);
        usersCars.setOwner(user.getUsername());
        usersCarsService.addUsersCars(usersCars);

        car.setAvailable(true);
        carService.save(car);

        return ResponseEntity.ok("success");
    }

    @Override
    public Object returnCar(String name, Long carId) {
        Optional<User> userOptional = findByUsername(name);
        Optional<Car> carOptional = carService.findByCarId(carId);
        if (carOptional.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машина с таким id не найдена"), HttpStatus.BAD_REQUEST);
        }

        Car car = carOptional.get();
        User user = userOptional.get();

        Optional<UsersCars> usersCarsOptional = usersCarsService.getUsersCarsWithHelpCar(car);
        if (usersCarsOptional.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "у пользователя нет этой машины"), HttpStatus.BAD_REQUEST);
        }

        UsersCars usersCars = usersCarsOptional.get();

        if (!(usersCars.getOwner().equals(name) && usersCars.getCar().getId().equals(carId))) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "у пользователя нет этой машины"), HttpStatus.BAD_REQUEST);
        }
        car.setAvailable(false);
        car.setMileage(car.getMileage() + 100);

        user.getCars().remove(car);
        userRepository.save(user);

        carService.updateCarUsersCars(usersCars.getId());
        usersCarsService.deleteById(usersCars.getId());
        return ResponseEntity.ok("success");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<List<UsersCars>> getAllRentedCars() {
        return ResponseEntity.ok(usersCarsService.getAllUsersCars());
    }
}
