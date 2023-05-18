package org.example.ilyaskalimullinn.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.uri}")
public class MainController {

    @RequestMapping("/test")
    public ResponseEntity<?> testApiWorking() {
        return new ResponseEntity<>("Api working", HttpStatus.OK);
    }

    @RequestMapping("/test/anyone")
    public ResponseEntity<?> testNoAuth() {
        return ResponseEntity.ok("Anyone can see this.");
    }

    @RequestMapping("/test/authenticated")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> testAuth() {
        return ResponseEntity.ok("Only authenticated users can see this.");
    }
}
