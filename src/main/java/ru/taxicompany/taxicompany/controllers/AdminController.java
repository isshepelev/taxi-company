package ru.taxicompany.taxicompany.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taxicompany.taxicompany.dto.CarDTO;
import ru.taxicompany.taxicompany.service.AdminService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add-car")
    public ResponseEntity<?> addNewCar(@RequestBody CarDTO carDTO){
        return adminService.addCar(carDTO);
    }
    @PostMapping("/delete-car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        return adminService.deleteCar(id);
    }
}
