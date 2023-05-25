package org.example.ilyaskalimullinn.notes.data.serializer.dictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meaning {
    private String partOfSpeech;
    List<Definition> definitions;
}
