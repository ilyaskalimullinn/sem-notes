package org.example.ilyaskalimullinn.notes.data.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ValidationErrorResponse {
    private String detail;
    private Map<String, String> errors;
}
