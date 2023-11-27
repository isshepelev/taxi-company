package ru.taxicompany.taxicompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taxicompany.taxicompany.domain.UsersCars;

@Repository
public interface UsersCarsRepository extends JpaRepository<UsersCars, Long> {
}
