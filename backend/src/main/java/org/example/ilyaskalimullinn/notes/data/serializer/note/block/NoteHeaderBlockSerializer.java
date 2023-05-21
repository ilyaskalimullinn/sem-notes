package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteHeaderBlockSerializer implements NoteBlockSerializer {
    private String id;
    private NoteHeaderBlockDataSerializer data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoteHeaderBlockDataSerializer {
        private String text;
        private Integer level;
    }
}
