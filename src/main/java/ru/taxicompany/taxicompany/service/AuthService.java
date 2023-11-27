package ru.taxicompany.taxicompany.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.taxicompany.taxicompany.dto.JwtRequest;
import ru.taxicompany.taxicompany.dto.RegistrationUserDTO;

public interface AuthService {
    ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest);

    ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDTO);
}
