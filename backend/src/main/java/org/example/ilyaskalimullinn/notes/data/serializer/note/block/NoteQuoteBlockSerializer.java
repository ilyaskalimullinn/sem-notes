package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteQuoteBlockSerializer implements NoteBlockSerializer {
    private String id;
    private NoteQuoteBlockDataSerializer data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoteQuoteBlockDataSerializer {
        private String text;
        private String caption;
        private String alignment;
    }
}
