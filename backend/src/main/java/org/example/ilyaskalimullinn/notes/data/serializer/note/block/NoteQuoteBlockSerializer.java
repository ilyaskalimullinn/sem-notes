package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.NoteQuoteBlockDataSerializer;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteQuoteBlockSerializer implements NoteBlockSerializer {
    private String id;
    private NoteQuoteBlockDataSerializer data;
}
