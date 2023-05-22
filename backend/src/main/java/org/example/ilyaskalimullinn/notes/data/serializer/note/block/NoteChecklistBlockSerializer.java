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
public class NoteChecklistBlockSerializer implements NoteBlockSerializer {
    private String id;
    private NoteChecklistBlockDataSerializer data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoteChecklistBlockDataSerializer {
        private List<NoteChecklistItemSerializer> items;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoteChecklistItemSerializer {
        private String text;
        private Boolean checked;
    }
}
