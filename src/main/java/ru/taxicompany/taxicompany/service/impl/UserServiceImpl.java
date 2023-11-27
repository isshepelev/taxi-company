package ru.taxicompany.taxicompany.service.impl;

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
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private CarService carService;
    private UsersCarsService usersCarsService;

    @Autowired
    public void setUsersCarsService(UsersCarsService usersCarsService) {
        this.usersCarsService = usersCarsService;
    }
    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден",username)));

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
        if (carOptional.isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машина с таким id не найдена"), HttpStatus.BAD_REQUEST);
        }

        Car car = carOptional.get();
        User user = userOptional.get();

        if (car.isAvailable()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машина с таким id уже занята"), HttpStatus.BAD_REQUEST);
        }

        user.getCars().add(car);
        userRepository.save(user);

        UsersCars usersCars = new UsersCars();
        usersCars.setCar(car);
        usersCars.setUser(user);
        usersCarsService.addUsersCars(usersCars);

        car.setAvailable(true);
        carService.save(car);

        return ResponseEntity.ok("success");
    }

    @Override
    public ResponseEntity<List<UsersCars>> getAllRentedCars() {
        return ResponseEntity.ok(usersCarsService.getAllUsersCars());
    }
}
