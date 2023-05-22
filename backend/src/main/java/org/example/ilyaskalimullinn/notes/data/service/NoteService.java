package org.example.ilyaskalimullinn.notes.data.service;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    public void saveNote(NoteSerializer noteSerializer, User user) {
        List<NoteBlockSerializer> blocks = noteSerializer.getContent().getBlocks();

        Note note = Note.builder()
                .title(noteSerializer.getTitle())
                .author(user)
                .editorVersion(noteSerializer.getContent().getVersion())
                .blocks(new ArrayList<>())
                .build();
    }
}
