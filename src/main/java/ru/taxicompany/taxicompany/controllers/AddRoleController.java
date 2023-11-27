package ru.taxicompany.taxicompany.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.taxicompany.taxicompany.domain.Role;
import ru.taxicompany.taxicompany.repository.RoleRepository;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class AddRoleController {
    private final RoleRepository roleRepository;

    @GetMapping("/user")
    public void roleUser() {
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
    }

    @GetMapping("/admin")
    public void roleAdmin() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);
    }
}
