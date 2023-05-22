package org.example.ilyaskalimullinn.notes.data.serializer.note.block.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteListBlockDataSerializer {
    private String style;
    private List<String> items;
}
