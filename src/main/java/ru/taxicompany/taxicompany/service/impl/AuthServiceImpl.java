package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.taxicompany.taxicompany.domain.User;
import ru.taxicompany.taxicompany.dto.JwtRequest;
import ru.taxicompany.taxicompany.dto.JwtResponse;
import ru.taxicompany.taxicompany.dto.RegistrationUserDTO;
import ru.taxicompany.taxicompany.exception.AppError;
import ru.taxicompany.taxicompany.service.AuthService;
import ru.taxicompany.taxicompany.service.UserService;
import ru.taxicompany.taxicompany.util.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createToken(@RequestBody JwtRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDTO) {
        if (!registrationUserDTO.getPassword().equals(registrationUserDTO.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDTO.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует " + registrationUserDTO.getUsername()), HttpStatus.BAD_REQUEST);
        }
        userService.createNewUser(registrationUserDTO);
        return ResponseEntity.ok("successful");
    }
}
