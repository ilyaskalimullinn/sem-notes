package org.example.ilyaskalimullinn.notes.data.serializer.note;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class NoteSerializer {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private NoteContentSerializer content;
}
