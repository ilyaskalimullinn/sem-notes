package org.example.ilyaskalimullinn.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @RequestMapping("/test")
    public ResponseEntity<?> testApiWorking() {
        return new ResponseEntity<>("Api working", HttpStatus.OK);
    }
}
