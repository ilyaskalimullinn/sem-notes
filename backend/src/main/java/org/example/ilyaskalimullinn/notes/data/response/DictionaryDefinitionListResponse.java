package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;
import org.example.ilyaskalimullinn.notes.data.serializer.dictionary.Definition;

import java.util.List;

@Data
@Builder
public class DictionaryDefinitionListResponse {
    private List<Definition> definitions;
}
