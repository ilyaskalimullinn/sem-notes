package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteEditSerializer;

@Data
@Builder
public class NoteEditResponse {
    private String detail;
    private NoteEditSerializer note;
}
