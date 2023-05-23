package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.NoteCodeBlockDataSerializer;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteCodeBlockSerializer implements NoteBlockSerializer {
    private String id;
    @NotNull
    private NoteCodeBlockDataSerializer data;
}
