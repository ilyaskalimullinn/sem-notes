package org.example.ilyaskalimullinn.notes.data.serializer.note;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NoteBriefSerializer {
    private Long id;
    private String title;
    private List<Long> categoryIds;
}
