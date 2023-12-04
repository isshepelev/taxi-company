package ru.taxicompany.taxicompany.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taxicompany.taxicompany.domain.UsersCars;
import ru.taxicompany.taxicompany.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/rent-car/{carId}")
    public ResponseEntity<?> userRentCar(Principal principal, @PathVariable Long carId) {
        return ResponseEntity.ok(userService.rentCar(principal.getName(), carId));
    }

    @PostMapping("/return-car/{carId}")
    public ResponseEntity<?> userReturnCar(Principal principal, @PathVariable Long carId) {
        return ResponseEntity.ok(userService.returnCar(principal.getName(), carId));
    }
}
