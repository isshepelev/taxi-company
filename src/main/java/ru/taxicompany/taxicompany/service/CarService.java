package ru.taxicompany.taxicompany.service;

import org.springframework.http.ResponseEntity;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.dto.CarDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAllCars();

    Car addNewCar(CarDTO carDTO);

    Optional<Car> findByCarId(Long id);

    void deleteCar(Long id);

    void save(Car car);

    void updateCarUsersCars(Long id);
}
