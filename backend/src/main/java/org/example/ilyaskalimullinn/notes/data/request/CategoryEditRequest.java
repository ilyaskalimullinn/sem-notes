package org.example.ilyaskalimullinn.notes.data.request;

import lombok.*;
import org.example.ilyaskalimullinn.notes.data.serializer.note.CategorySerializer;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CategoryEditRequest {
    @NotNull
    private CategorySerializer category;
}
