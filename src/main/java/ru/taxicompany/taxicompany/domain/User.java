package ru.taxicompany.taxicompany.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;

    @ManyToMany
    @JoinColumn(name = "users_role")
    private Collection<Role> roles;
}
