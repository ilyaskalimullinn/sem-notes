package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "paragraph", value = NoteParagraphBlockSerializer.class),
})
public interface NoteBlockSerializer {
}
