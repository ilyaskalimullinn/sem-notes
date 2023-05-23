package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteDeleteResponse {
    private String detail;
    private Long id;
}
