package ru.taxicompany.taxicompany.service;

import org.springframework.http.ResponseEntity;
import ru.taxicompany.taxicompany.dto.CarDTO;

public interface AdminService {
    ResponseEntity<?> addCar(CarDTO carDTO);

    ResponseEntity<?> deleteCar(Long id);
}
