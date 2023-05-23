package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.response.NoteEditResponse;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteContentSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteEditSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteParagraphBlockSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.NoteParagraphBlockDataSerializer;
import org.example.ilyaskalimullinn.notes.data.service.NoteService;
import org.example.ilyaskalimullinn.notes.data.service.UserDetailsServiceImpl;
import org.example.ilyaskalimullinn.notes.data.service.UserService;
import org.example.ilyaskalimullinn.notes.exception.FieldsValidationException;
import org.example.ilyaskalimullinn.notes.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return noteService.saveNote(noteSerializer, user);
    }

    @GetMapping("/{noteId}")
    public NoteSerializer get(@PathVariable("noteId") Long noteId,
                              Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return this.noteService.getSerializedNote(noteId, user);
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
}
