package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.response.NoteDeleteResponse;
import org.example.ilyaskalimullinn.notes.data.response.NoteEditResponse;
import org.example.ilyaskalimullinn.notes.data.response.NoteBriefResponse;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.service.NoteService;
import org.example.ilyaskalimullinn.notes.exception.FieldsValidationException;
import org.example.ilyaskalimullinn.notes.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("${api.uri}/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteEditResponse save(@RequestBody @Valid NoteSerializer noteSerializer,
                                 BindingResult result,
                                 Principal principal) {
        if (result.hasErrors()) {
            throw new FieldsValidationException("Errors in note request", result.getFieldErrors());
        }
        if (noteSerializer.getId() != null) {
            throw new InvalidRequestException("Bad request, new note already has an ID");
        }
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return noteService.saveNote(noteSerializer, user);
    }

    @GetMapping("/{noteId}")
    public NoteSerializer get(@PathVariable("noteId") Long noteId,
                              Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return this.noteService.getSerializedNote(noteId, user);
    }

    @GetMapping("")
    public NoteBriefResponse list(@RequestParam Integer page, @RequestParam Integer size,
                                  Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return this.noteService.getNotesBrief(user, page, size);
    }

    @PutMapping("/{noteId}")
    public NoteEditResponse update(@PathVariable("noteId") Long noteId,
            @RequestBody @Valid NoteSerializer noteSerializer,
                                 BindingResult result,
                                 Principal principal) {
        if (result.hasErrors()) {
            throw new FieldsValidationException("Errors in note request", result.getFieldErrors());
        }
        if (!noteId.equals(noteSerializer.getId())) {
            throw new InvalidRequestException("Note ID's mismatch");
        }
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return noteService.updateNote(noteSerializer, user);
    }

    @DeleteMapping("/{noteId}")
    public NoteDeleteResponse delete(@PathVariable("noteId") Long noteId,
                                     Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return noteService.deleteNoteById(noteId, user);
    }
}
