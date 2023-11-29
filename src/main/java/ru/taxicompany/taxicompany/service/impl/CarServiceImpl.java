package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.dto.CarDTO;
import ru.taxicompany.taxicompany.repository.CarRepository;
import ru.taxicompany.taxicompany.service.CarService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;


    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car addNewCar(CarDTO carDTO) {
        Car car = new Car();
        car.setYear(carDTO.getYear());
        car.setPrice(carDTO.getPrice());
        car.setModel(carDTO.getModel());
        car.setManufacturer(carDTO.getManufacturer());
        car.setMileage(0);
        car.setAvailable(false);
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> findByCarId(Long id){
        return carRepository.findById(id);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void updateCarUsersCars(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            carRepository.save(car);
        }
    }

}
