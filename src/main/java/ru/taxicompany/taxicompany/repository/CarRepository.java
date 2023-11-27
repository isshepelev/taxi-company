package ru.taxicompany.taxicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taxicompany.taxicompany.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
