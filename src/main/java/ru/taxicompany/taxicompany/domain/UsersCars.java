package ru.taxicompany.taxicompany.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UsersCars {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usersCars_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "usersCars_car")
    private Car car;
}
