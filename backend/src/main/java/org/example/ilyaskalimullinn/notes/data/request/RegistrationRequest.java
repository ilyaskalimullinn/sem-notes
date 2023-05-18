package org.example.ilyaskalimullinn.notes.data.request;

import lombok.Data;

@Data
public class RegistrationRequest {
    //todo add validation, including password and passwordRepeat
    private String fullName;
    private String username;
    private String password;
    private String passwordRepeat;
}
