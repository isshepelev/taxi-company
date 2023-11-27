package ru.taxicompany.taxicompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class UserDTO {
    private String username;
    private String password;

    public UserDTO(String username) {
        this.username = username;
    }
}
