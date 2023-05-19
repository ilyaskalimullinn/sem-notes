package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.serializer.UserDetailsSerializer;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private UserDetailsSerializer user;
}
