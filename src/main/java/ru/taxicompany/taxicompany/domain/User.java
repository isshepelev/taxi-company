package ru.taxicompany.taxicompany.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;
    private String password;
    private String email;

    @ManyToMany
    @JoinColumn(name = "users_role")
    private Collection<Role> roles;

    @OneToMany
    @JoinColumn(name = "users_cars")
    private List<Car> cars = new ArrayList<>();
}
