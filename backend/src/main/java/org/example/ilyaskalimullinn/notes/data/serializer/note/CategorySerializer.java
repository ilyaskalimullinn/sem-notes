package org.example.ilyaskalimullinn.notes.data.serializer.note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ilyaskalimullinn.notes.data.entity.note.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CategorySerializer {
    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;
}
