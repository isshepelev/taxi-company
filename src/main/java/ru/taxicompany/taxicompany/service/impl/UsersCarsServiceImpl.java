package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taxicompany.taxicompany.domain.Car;
import ru.taxicompany.taxicompany.domain.UsersCars;
import ru.taxicompany.taxicompany.repository.UsersCarsRepository;
import ru.taxicompany.taxicompany.service.UsersCarsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersCarsServiceImpl implements UsersCarsService {
    private final UsersCarsRepository usersCarsRepository;

    @Override
    public void addUsersCars(UsersCars usersCars){
        usersCarsRepository.save(usersCars);
    }

    @Override
    public List<UsersCars> getAllUsersCars(){
        return usersCarsRepository.findAll();
    }

    @Override
    public Optional<UsersCars> getUsersCarsWithHelpCar(Car car) {
        return usersCarsRepository.findByCar(car);
    }

    @Override
    public void deleteById(Long id) {
        usersCarsRepository.deleteById(id);
    }
}
