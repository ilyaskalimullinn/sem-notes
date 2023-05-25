package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.response.DictionaryDefinitionListResponse;
import org.example.ilyaskalimullinn.notes.data.serializer.dictionary.Definition;
import org.example.ilyaskalimullinn.notes.data.service.DictionaryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.uri}/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryApiService dictionaryApiService;

    @GetMapping("/{word}")
    public DictionaryDefinitionListResponse getWordDefinitionList(@PathVariable String word) {
        List<Definition> definitions = dictionaryApiService.getDefinitionList(word);

        return DictionaryDefinitionListResponse.builder()
                .definitions(definitions)
                .build();
    }
}