package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.dto.CarDTO;
import ru.taxicompany.taxicompany.exception.AppError;
import ru.taxicompany.taxicompany.service.AdminService;
import ru.taxicompany.taxicompany.service.CarService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CarService carService;


    @Override
    public ResponseEntity<?> addCar(CarDTO carDTO) {
        if (!(carDTO.getYear() > 1930 && carDTO.getYear() < 2024)){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "указанный вами год не соответствует норме"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(carService.addNewCar(carDTO));
    }

    @Override
    public ResponseEntity<?> deleteCar(Long id) {
        Optional<Car> car = carService.findByCarId(id);
        if (car.isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машины с таким id не существует"), HttpStatus.BAD_REQUEST);
        }
        carService.deleteCar(id);
        return ResponseEntity.ok("success");
    }
}
