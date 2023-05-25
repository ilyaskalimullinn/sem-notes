package org.example.ilyaskalimullinn.notes.data.serializer.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Word {
    private String word;
    private String phonetic;
    private List<Phonetics> phonetics;
    private String origin;
    List<Meaning> meanings;
}
