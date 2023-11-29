package ru.taxicompany.taxicompany.service;

import org.springframework.http.ResponseEntity;
import ru.taxicompany.taxicompany.domain.User;
import ru.taxicompany.taxicompany.dto.CarDTO;

import java.util.List;

public interface AdminService {
    ResponseEntity<?> addCar(CarDTO carDTO);

    ResponseEntity<?> deleteCar(Long id);

    ResponseEntity<List<User>> getAllUser();
}
