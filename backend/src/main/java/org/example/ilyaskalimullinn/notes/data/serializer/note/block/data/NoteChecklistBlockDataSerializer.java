package org.example.ilyaskalimullinn.notes.data.serializer.note.block.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteChecklistBlockSerializer;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteChecklistBlockDataSerializer {
    private List<NoteChecklistBlockItemSerializer> items;
}
