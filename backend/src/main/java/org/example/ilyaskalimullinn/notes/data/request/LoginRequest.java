package org.example.ilyaskalimullinn.notes.data.request;

import lombok.Data;

@Data
public class LoginRequest {
    //todo add validation
    private String username;
    private String password;
}
