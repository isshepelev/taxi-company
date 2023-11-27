package ru.taxicompany.taxicompany.dto;

import lombok.Data;

@Data
public class CarDTO {
    private String model;
    private double price;
    private String manufacturer;
    private int year;
}
