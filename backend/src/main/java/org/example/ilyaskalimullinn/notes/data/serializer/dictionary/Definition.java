package org.example.ilyaskalimullinn.notes.data.serializer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Definition {
    private String definition;
    private String example;
    private List<String> synonyms;
    private List<String> antonyms;
}
