package org.example.ilyaskalimullinn.notes.data.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.ilyaskalimullinn.notes.data.serializer.dictionary.Definition;
import org.example.ilyaskalimullinn.notes.data.serializer.dictionary.Word;
import org.example.ilyaskalimullinn.notes.exception.DictionaryApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryApiService {
    @Value("${dictionary.api.url}")
    private String API_URL;

    private OkHttpClient client;
    private ObjectMapper objectMapper;

    public DictionaryApiService() {
        client = new OkHttpClient();

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Definition> getDefinitionList(String word) {


        Request request = new Request.Builder()
                .url(API_URL + "/" + word)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            List<Word> words = objectMapper.readValue(responseBody, new TypeReference<List<Word>>() {});


            return words.stream()
                    .flatMap(w -> w.getMeanings().stream())
                    .flatMap(meaning -> meaning.getDefinitions().stream())
                    .toList();
        } catch (Exception e) {
            throw new DictionaryApiException("Dictionary API not responding", e);
        }
    }
}
