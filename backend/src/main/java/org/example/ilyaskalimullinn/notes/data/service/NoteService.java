package org.example.ilyaskalimullinn.notes.data.service;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.example.ilyaskalimullinn.notes.data.repository.NoteRepository;
import org.example.ilyaskalimullinn.notes.data.response.NoteEditResponse;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteEditSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;
import org.example.ilyaskalimullinn.notes.exception.NotFoundException;
import org.example.ilyaskalimullinn.notes.exception.NotePersistenceException;
import org.example.ilyaskalimullinn.notes.util.converter.NoteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteConverter noteConverter;

    @Autowired
    private NoteRepository noteRepository;

    public NoteEditResponse saveNote(NoteSerializer noteSerializer, User user) {
        try {
            Note note = (Note) noteConverter.convert(noteSerializer, TypeDescriptor.valueOf(noteSerializer.getClass()),
                    TypeDescriptor.valueOf(Note.class));
            note.setAuthor(user);

            noteRepository.save(note);

            return NoteEditResponse.builder()
                    .detail("Created")
                    .note(new NoteEditSerializer(note))
                    .build();
        } catch (Exception e) {
            throw new NotePersistenceException("Something went wrong, could not save note. Please, try again");
        }

    }

    public Note getNote(Long noteId, User user) {
        return noteRepository.findByIdAndAuthor(noteId, user);
    }

    public NoteSerializer getSerializedNote(Long noteId, User user) {
        // todo exception handling, 404
        Note note = noteRepository.findByIdAndAuthor(noteId, user);

        if (note == null) {
            throw new NotFoundException("Note not found");
        }

        return (NoteSerializer) noteConverter.convert(note,
                TypeDescriptor.valueOf(Note.class),
                TypeDescriptor.valueOf(NoteSerializer.class));
    }
}
