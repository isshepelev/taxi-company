package ru.taxicompany.taxicompany.service;

import ru.taxicompany.taxicompany.domain.UsersCars;

import java.util.List;

public interface UsersCarsService {
    void addUsersCars(UsersCars usersCars);

    List<UsersCars> getAllUsersCars();
}
