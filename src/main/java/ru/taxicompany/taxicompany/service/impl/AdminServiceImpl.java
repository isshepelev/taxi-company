package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.domain.Role;
import ru.taxicompany.taxicompany.domain.User;
import ru.taxicompany.taxicompany.dto.CarDTO;
import ru.taxicompany.taxicompany.exception.AppError;
import ru.taxicompany.taxicompany.service.AdminService;
import ru.taxicompany.taxicompany.service.CarService;
import ru.taxicompany.taxicompany.service.UserService;
import ru.taxicompany.taxicompany.service.UsersCarsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CarService carService;
    private final UserService userService;
    private final UsersCarsService usersCarsService;

    @Override
    public ResponseEntity<?> addCar(CarDTO carDTO) {
        if (!(carDTO.getYear() > 1900 && carDTO.getYear() < 2024)) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "указанный вами год не соответствует норме"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(carService.addNewCar(carDTO));
    }

    @Override
    public ResponseEntity<?> deleteCar(Long id) {
        Optional<Car> carOptional = carService.findByCarId(id);
        Car car = carOptional.get();
        if (carOptional.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машины с таким id не существует"), HttpStatus.BAD_REQUEST);
        }
        if (usersCarsService.getUsersCarsWithHelpCar(car).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машина находится в пользовании ее нельзя удалить"), HttpStatus.BAD_REQUEST);
        }
        carService.deleteCar(id);
        return ResponseEntity.ok("success");
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<?> deleteUser(String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователя с именем " + username + " не существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        if (!user.getCars().isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователь не может быть удален так как он арендует машину"), HttpStatus.BAD_REQUEST);
        }
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("success");
    }

    @Override
    public ResponseEntity<?> giveAdmin(String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователя не существует"), HttpStatus.BAD_REQUEST);
        }

        User user = userOptional.get();
        if (user.getRoles().stream().map(Role::getName).anyMatch(name -> name.equals("ROLE_ADMIN"))) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователь уже администратор"), HttpStatus.BAD_REQUEST);
        }
        Role role = new Role();
        role.setId(2L);
        role.setName("ROLE_ADMIN");
        List<Role> roleAdminList = new ArrayList<>();
        roleAdminList.add(role);
        user.setRoles(roleAdminList);
        userService.save(user);
        return ResponseEntity.ok("success");
    }
}
