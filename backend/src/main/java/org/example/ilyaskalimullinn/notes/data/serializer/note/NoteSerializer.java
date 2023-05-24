package org.example.ilyaskalimullinn.notes.data.serializer.note;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class NoteSerializer {
    // nullable
    private Long id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private NoteContentSerializer content;
    @NotNull // but might be empty
    private List<Long> categoryIds;
}
