package ru.taxicompany.taxicompany.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RentAndReturnRabbit implements Serializable {
    private String email;
    private String name;
    private Car car;
}
