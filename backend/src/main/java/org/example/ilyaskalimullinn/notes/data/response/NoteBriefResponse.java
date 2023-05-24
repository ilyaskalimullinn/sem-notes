package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteBriefSerializer;

import java.util.List;

@Data
@Builder
public class NoteBriefResponse {
    private Integer page;
    private Integer size;
    private List<NoteBriefSerializer> notes;
}
