package ru.taxicompany.taxicompany.domain;


import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Hidden
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
