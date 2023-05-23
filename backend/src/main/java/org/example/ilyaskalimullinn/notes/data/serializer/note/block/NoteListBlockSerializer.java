package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.NoteListBlockDataSerializer;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteListBlockSerializer implements NoteBlockSerializer {
    private String id;
    @NotNull
    private NoteListBlockDataSerializer data;
}
