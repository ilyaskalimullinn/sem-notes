package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.serializer.note.CategorySerializer;

import java.util.List;

@Data
@Builder
public class CategoryListResponse {
    private List<CategorySerializer> categories;
}
