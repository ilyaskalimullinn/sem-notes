package org.example.ilyaskalimullinn.notes.data.service;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.example.ilyaskalimullinn.notes.data.repository.NoteRepository;
import org.example.ilyaskalimullinn.notes.data.response.NoteDeleteResponse;
import org.example.ilyaskalimullinn.notes.data.response.NoteEditResponse;
import org.example.ilyaskalimullinn.notes.data.response.NoteBriefResponse;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteBriefSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteEditSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.exception.InvalidRequestException;
import org.example.ilyaskalimullinn.notes.exception.NotFoundException;
import org.example.ilyaskalimullinn.notes.exception.EntityPersistenceException;
import org.example.ilyaskalimullinn.notes.util.converter.NoteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
            throw new EntityPersistenceException("Something went wrong, could not save note. Please, try again");
        }

    }

    public Note getNote(Long noteId, User user) {
        return noteRepository.findByIdAndAuthor(noteId, user);
    }

    public NoteSerializer getSerializedNote(Long noteId, User user) {
        Note note = noteRepository.findByIdAndAuthor(noteId, user);

        if (note == null) {
            throw new NotFoundException("Note not found");
        }

        return (NoteSerializer) noteConverter.convert(note,
                TypeDescriptor.valueOf(Note.class),
                TypeDescriptor.valueOf(NoteSerializer.class));
    }

    public NoteEditResponse updateNote(NoteSerializer noteSerializer, User user) {
        if (!noteRepository.existsByIdAndAuthor(noteSerializer.getId(), user)) {
            throw new NotFoundException("Can't find a note");
        }
        try {
            Note note = (Note) noteConverter.convert(noteSerializer, TypeDescriptor.valueOf(noteSerializer.getClass()),
                    TypeDescriptor.valueOf(Note.class));
            note.setAuthor(user);

            noteRepository.save(note);

            return NoteEditResponse.builder()
                    .detail("Updated")
                    .note(new NoteEditSerializer(note))
                    .build();
        } catch (Exception e) {
            throw new EntityPersistenceException("Something went wrong, could not update note. Please, try again");
        }
    }

    public NoteDeleteResponse deleteNoteById(Long noteId, User user) {
        try {

            noteRepository.deleteByIdAndAuthor(noteId, user);

            return NoteDeleteResponse.builder()
                    .detail("Deleted")
                    .id(noteId)
                    .build();
        } catch (Exception e) {
            throw new EntityPersistenceException("Something went wrong, could not delete note. Please, try again");
        }
    }

    public NoteBriefResponse getNotesBrief(User user, Integer page, Integer size) {
        // TODO !!!!! OPTIMIZE QUERIES!
        try {

            List<NoteBriefSerializer> notes = noteRepository.findByAuthorOrderByUpdatedAtAsc(user, PageRequest.of(page, size))
                    .stream()
                    .map(note -> NoteBriefSerializer.builder()
                            .title(note.getTitle())
                            .id(note.getId())
                            .build())
                    .toList();

            return NoteBriefResponse.builder()
                    .page(page)
                    .size(size)
                    .notes(notes)
                    .build();
        } catch (Exception e) {
            throw new EntityPersistenceException("Something went wrong, could not find notes. Please, try again");
        }
    }
}
