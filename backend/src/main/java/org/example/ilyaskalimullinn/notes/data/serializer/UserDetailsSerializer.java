package org.example.ilyaskalimullinn.notes.data.serializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.entity.User;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsSerializer {
    private String username;
    private String fullName;
    private Date createdAt;

    public UserDetailsSerializer(User user) {
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.createdAt = user.getCreatedAt();
    }
}
