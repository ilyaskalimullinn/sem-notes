package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.NoteHeaderBlockDataSerializer;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteHeaderBlockSerializer implements NoteBlockSerializer {
    private String id;
    @NotNull
    private NoteHeaderBlockDataSerializer data;
}
