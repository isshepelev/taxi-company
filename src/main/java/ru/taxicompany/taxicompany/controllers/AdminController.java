package ru.taxicompany.taxicompany.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taxicompany.taxicompany.domain.User;
import ru.taxicompany.taxicompany.dto.CarDTO;
import ru.taxicompany.taxicompany.service.AdminService;

import java.util.List;

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

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return adminService.getAllUser();
    }

    @PostMapping("/delete/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username){
        return adminService.deleteUser(username);
    }
    @PostMapping("/give-role/{username}")
    public ResponseEntity<?> givRoleAdminToUser(@PathVariable String username){
        return adminService.giveAdmin(username);
    }
}
