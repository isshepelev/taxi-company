package ru.taxicompany.taxicompany.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.service.AllService;

@RestController
@RequestMapping("/all")
@RequiredArgsConstructor
public class AllController {
    private final AllService allService;

    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars(){
        return allService.getAll();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCat(@PathVariable Long id){
        return allService.getCar(id);
    }
}
