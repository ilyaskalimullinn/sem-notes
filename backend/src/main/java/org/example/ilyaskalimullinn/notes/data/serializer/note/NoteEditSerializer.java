package org.example.ilyaskalimullinn.notes.data.serializer.note;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;

import java.util.Date;

@Data
public class NoteEditSerializer {
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public NoteEditSerializer(Note note) {
        this.id = note.getId();
        this.createdAt = note.getCreatedAt();
        this.updatedAt = note.getUpdatedAt();
    }
}
