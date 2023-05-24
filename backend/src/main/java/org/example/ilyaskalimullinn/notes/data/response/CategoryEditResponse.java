package org.example.ilyaskalimullinn.notes.data.response;

import lombok.*;
import org.example.ilyaskalimullinn.notes.data.serializer.note.CategorySerializer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CategoryEditResponse {
    private String detail;
    private CategorySerializer category;
}
