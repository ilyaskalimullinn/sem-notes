package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GenericErrorResponse {
    private List<String> errors;
}
