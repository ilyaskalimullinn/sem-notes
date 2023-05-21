package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteContentSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteBlockSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.NoteParagraphBlockSerializer;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.uri}/notes")
public class NoteController {
    @PostMapping("")
    public String post(@RequestBody @Valid NoteSerializer noteSerializer,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "no";
        }
        System.out.println(noteSerializer);
        return "test";
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
