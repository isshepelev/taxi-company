package ru.taxicompany.taxicompany.service;

import org.springframework.http.ResponseEntity;
import ru.taxicompany.taxicompany.domain.Car;

public interface AllService {
    ResponseEntity<?> getAll();

    ResponseEntity<?> getCar(Long id);

}
