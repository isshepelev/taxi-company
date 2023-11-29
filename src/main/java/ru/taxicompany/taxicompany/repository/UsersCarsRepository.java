package ru.taxicompany.taxicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.domain.UsersCars;

import java.util.Optional;

@Repository
public interface UsersCarsRepository extends JpaRepository<UsersCars, Long> {
    Optional<UsersCars> findByCar(Car car);
}
