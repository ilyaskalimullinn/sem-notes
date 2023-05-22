package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteListBlockSerializer implements NoteBlockSerializer {
    private String id;
    private NoteListBlockDataSerializer data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoteListBlockDataSerializer {
        private String style;
        private List<String> items;
    }
}
