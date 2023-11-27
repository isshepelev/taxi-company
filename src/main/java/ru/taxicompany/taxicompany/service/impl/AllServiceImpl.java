package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.exception.AppError;
import ru.taxicompany.taxicompany.service.AllService;
import ru.taxicompany.taxicompany.service.CarService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllServiceImpl implements AllService {
    private final CarService carService;


    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @Override
    public ResponseEntity<?> getCar(Long id) {
        Optional<Car> car = carService.findByCarId(id);
        if (car.isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "машины с таким id не существует"),HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(carService.findByCarId(id));
    }
}
