package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.request.LoginRequest;
import org.example.ilyaskalimullinn.notes.data.request.RegistrationRequest;
import org.example.ilyaskalimullinn.notes.data.response.AuthenticationResponse;
import org.example.ilyaskalimullinn.notes.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.uri}/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@RequestBody @Valid RegistrationRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }
}
