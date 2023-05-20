package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.request.LoginRequest;
import org.example.ilyaskalimullinn.notes.data.request.RegistrationRequest;
import org.example.ilyaskalimullinn.notes.data.response.AuthenticationResponse;
import org.example.ilyaskalimullinn.notes.data.service.UserService;
import org.example.ilyaskalimullinn.notes.exception.FieldsValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.uri}/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@RequestBody @Valid RegistrationRequest request,
                                           BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField,
                            DefaultMessageSourceResolvable::getDefaultMessage));
            throw new FieldsValidationException("Fields filled with errors", errors);
        }
        return userService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse login(@RequestBody @Valid LoginRequest request,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField,
                            DefaultMessageSourceResolvable::getDefaultMessage));
            throw new FieldsValidationException("Fields filled with errors", errors);
        }
        return userService.login(request);
    }
}
