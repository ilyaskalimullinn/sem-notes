package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.NoteParagraphBlockDataSerializer;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteParagraphBlockSerializer implements NoteBlockSerializer {
    private String id;
    private NoteParagraphBlockDataSerializer data;

}
