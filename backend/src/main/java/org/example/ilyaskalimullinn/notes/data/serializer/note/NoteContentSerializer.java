package org.example.ilyaskalimullinn.notes.data.serializer.note;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class NoteContentSerializer {
    @NotNull
    @NotBlank
    private String version;
    private Long time;
    @NotNull
    private List<NoteBlockSerializer> blocks;
}
