package org.example.ilyaskalimullinn.notes.data.request;

import lombok.Data;
import org.example.ilyaskalimullinn.notes.utils.validator.FieldMatch;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@FieldMatch.List({
    @FieldMatch(first = "password", second = "passwordRepeat", message = "The password fields must match")
})
public class RegistrationRequest {
    @NotNull
    private String fullName;

    @NotNull
    @Email
    @Length(min = 1, max = 255)
    private String username;

    @NotNull
    @Length(min = 5, max = 255)
    private String password;

    @NotNull
    @Length(min = 5, max = 255)
    private String passwordRepeat;
}
