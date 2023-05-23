package org.example.ilyaskalimullinn.notes.data.serializer.note.block.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteHeaderBlockDataSerializer {
    @NotNull
    private String text;
    @NotNull
    @Positive
    private Integer level;
}
