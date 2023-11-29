package ru.taxicompany.taxicompany.service;

import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.domain.UsersCars;

import java.util.List;
import java.util.Optional;

public interface UsersCarsService {
    void addUsersCars(UsersCars usersCars);

    List<UsersCars> getAllUsersCars();

    Optional<UsersCars> getUsersCarsWithHelpCar(Car car);

    void deleteById(Long id);
}
