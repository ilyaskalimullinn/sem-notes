package org.example.ilyaskalimullinn.notes.data.serializer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phonetics {
    private String text;
    private String audio;
    private String sourceUrl;
}
