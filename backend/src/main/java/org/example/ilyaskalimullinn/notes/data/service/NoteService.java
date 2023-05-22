package org.example.ilyaskalimullinn.notes.data.service;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.example.ilyaskalimullinn.notes.data.repository.NoteRepository;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;
import org.example.ilyaskalimullinn.notes.util.converter.NoteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteConverter noteConverter;

    @Autowired
    private NoteRepository noteRepository;

    public void saveNote(NoteSerializer noteSerializer, User user) {
        List<NoteBlockSerializer> blocks = noteSerializer.getContent().getBlocks();

        Note note = (Note) noteConverter.convert(noteSerializer, TypeDescriptor.valueOf(noteSerializer.getClass()), TypeDescriptor.valueOf(Note.class));
        note.setAuthor(user);

        System.out.println(note);

        noteRepository.save(note);

    }

    public Note getNote(Long noteId, User user) {
        return noteRepository.findByIdAndAuthor(noteId, user);
    }

    public NoteSerializer getSerializedNote(Long noteId, User user) {
        Note note = noteRepository.findByIdAndAuthor(noteId, user);
        return (NoteSerializer) noteConverter.convert(note,
                TypeDescriptor.valueOf(Note.class),
                TypeDescriptor.valueOf(NoteSerializer.class));
    }
}
