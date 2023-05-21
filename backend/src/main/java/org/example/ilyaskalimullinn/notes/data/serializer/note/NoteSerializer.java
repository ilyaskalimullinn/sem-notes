package org.example.ilyaskalimullinn.notes.data.serializer.note;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;

import java.util.List;

@Data
@Builder
public class NoteSerializer {
    private String version;
    private Long time;
    private List<NoteBlockSerializer> blocks;
}
