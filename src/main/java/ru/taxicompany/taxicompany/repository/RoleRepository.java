package ru.taxicompany.taxicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taxicompany.taxicompany.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
