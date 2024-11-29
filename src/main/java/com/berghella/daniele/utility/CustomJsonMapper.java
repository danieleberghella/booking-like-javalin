package com.berghella.daniele.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.json.JsonMapper;

import java.io.*;
import java.lang.reflect.Type;
import java.util.stream.Stream;

public class CustomJsonMapper implements JsonMapper {

    private final ObjectMapper objectMapper;

    public CustomJsonMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Supporto per LocalDate e altre date Java 8+
    }

    @Override
    public <T> T fromJsonStream(InputStream json, Type targetType) {
        try {
            return objectMapper.readValue(json, objectMapper.constructType(targetType));
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la deserializzazione JSON", e);
        }
    }

    @Override
    public <T> T fromJsonString(String json, Type targetType) {
        try {
            return objectMapper.readValue(json, objectMapper.constructType(targetType));
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la deserializzazione JSON da String", e);
        }
    }

    @Override
    public InputStream toJsonStream(Object obj, Type type) {
        try {
            String jsonString = objectMapper.writerFor(objectMapper.constructType(type)).writeValueAsString(obj);
            return new ByteArrayInputStream(jsonString.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la serializzazione JSON in stream", e);
        }
    }

    @Override
    public String toJsonString(Object obj, Type type) {
        try {
            return objectMapper.writerFor(objectMapper.constructType(type)).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la serializzazione JSON in stringa", e);
        }
    }

    @Override
    public void writeToOutputStream(Stream<?> stream, OutputStream outputStream) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            stream.forEach(obj -> {
                try {
                    writer.write(objectMapper.writeValueAsString(obj));
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException("Errore durante la scrittura del JSON nell'OutputStream", e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Errore generale durante la scrittura nel OutputStream", e);
        }
    }
}