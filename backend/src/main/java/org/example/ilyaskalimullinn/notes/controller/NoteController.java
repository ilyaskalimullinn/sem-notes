package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteContentSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteParagraphBlockSerializer;
import org.example.ilyaskalimullinn.notes.data.service.NoteService;
import org.example.ilyaskalimullinn.notes.data.service.UserDetailsServiceImpl;
import org.example.ilyaskalimullinn.notes.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.uri}/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("")
    public String save(@RequestBody NoteSerializer noteSerializer,
                       @AuthenticationPrincipal Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        noteService.saveNote(noteSerializer, user);
        System.out.println(noteSerializer);
        return "{}";
    }

    @GetMapping("/test")
    public NoteSerializer getTest() {
        List<NoteBlockSerializer> blocks = new ArrayList<>();
        blocks.add(NoteParagraphBlockSerializer.builder()
                        .id("dfdsfds")
                        .data(NoteParagraphBlockSerializer.NoteParagraphBlockDataSerializer.builder().text("TEXT").build())
                        .build());
        NoteContentSerializer content = NoteContentSerializer.builder()
                .time(100000L)
                .version("fdshfjkdmsl")
                .blocks(blocks)
                .build();
        return NoteSerializer.builder()
                .title("Note 1")
                .content(content)
                .build();
    }
}
