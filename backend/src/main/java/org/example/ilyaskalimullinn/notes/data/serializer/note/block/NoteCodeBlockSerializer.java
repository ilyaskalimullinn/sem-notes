package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteCodeBlockSerializer implements NoteBlockSerializer {
    private String id;
    private NoteCodeBlockDataSerializer data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoteCodeBlockDataSerializer {
        private String code;
    }
}
