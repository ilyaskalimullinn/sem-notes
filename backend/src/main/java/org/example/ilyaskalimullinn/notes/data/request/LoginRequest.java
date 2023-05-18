package org.example.ilyaskalimullinn.notes.data.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull
    @Email
    private String username;
    @NotNull
    @Length(min = 5, max = 255)
    private String password;
}
