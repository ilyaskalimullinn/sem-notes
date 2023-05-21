package org.example.ilyaskalimullinn.notes.data.serializer.note.block;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "paragraph", value = NoteParagraphBlockSerializer.class),
        @JsonSubTypes.Type(name = "header", value = NoteHeaderBlockSerializer.class),
        @JsonSubTypes.Type(name = "quote", value = NoteQuoteBlockSerializer.class),
        @JsonSubTypes.Type(name = "list", value = NoteListBlockSerializer.class),
        @JsonSubTypes.Type(name = "code", value = NoteCodeBlockSerializer.class),
        @JsonSubTypes.Type(name = "checkList", value = NoteChecklistBlockSerializer.class),
})
public interface NoteBlockSerializer {
}
