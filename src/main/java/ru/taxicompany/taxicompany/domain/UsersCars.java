package ru.taxicompany.taxicompany.domain;

import lombok.Data;
import ru.taxicompany.taxicompany.dto.UserInfoDTO;

import javax.persistence.*;

@Data
@Entity
public class UsersCars {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String owner;

    @OneToOne
    @JoinColumn(name = "usersCars_car")
    private Car car;
}
