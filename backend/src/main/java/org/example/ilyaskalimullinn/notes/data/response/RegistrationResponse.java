package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponse {
    private String token;
}
