package org.example.ilyaskalimullinn.notes.data.serializer.note.block.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteChecklistBlockItemSerializer {
    @NotNull
    private String text;
    @NotNull
    private Boolean checked;
}
