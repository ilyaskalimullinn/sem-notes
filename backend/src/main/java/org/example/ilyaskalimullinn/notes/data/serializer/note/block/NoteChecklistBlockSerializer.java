package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.NoteChecklistBlockDataSerializer;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteChecklistBlockSerializer implements NoteBlockSerializer {
    private String id;
    @NotNull
    private NoteChecklistBlockDataSerializer data;
}
