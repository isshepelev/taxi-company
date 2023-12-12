package ru.taxicompany.taxicompany.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegRabbit implements Serializable {
    private String name;
    private String email;
}
