package org.example.ilyaskalimullinn.notes.data.service;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.repository.UserRepository;
import org.example.ilyaskalimullinn.notes.data.request.LoginRequest;
import org.example.ilyaskalimullinn.notes.data.request.RegistrationRequest;
import org.example.ilyaskalimullinn.notes.data.response.AuthenticationResponse;
import org.example.ilyaskalimullinn.notes.data.serializer.UserDetailsSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateKeyException("User with this username already exists");
        }
        User user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        user = userRepository.save(user);
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .user(new UserDetailsSerializer(user))
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        try {
            authenticationManager.authenticate(authToken);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Wrong username and password");
        }
        User user = userRepository.findByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .user(new UserDetailsSerializer(user))
                .build();
    }
}
