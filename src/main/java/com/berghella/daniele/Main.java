package com.berghella.daniele;


import com.berghella.daniele.controller.UserController;

import java.io.InputStream;


public class Main {
    public static void main(String[] args) {

        // Configura l'ObjectMapper di Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Implementa il JsonMapper per Javalin
        JsonMapper jsonMapper = new JsonMapper() {
            @Override
            public <T> T fromJsonStream(InputStream jsonStream, Class<T> targetClass) {
                try {
                    return objectMapper.readValue(jsonStream, targetClass);
                } catch (Exception e) {
                    throw new RuntimeException("Errore durante la deserializzazione JSON", e);
                }
            }

            @Override
            public String toJsonString(Object obj) {
                try {
                    return objectMapper.writeValueAsString(obj);
                } catch (Exception e) {
                    throw new RuntimeException("Errore durante la serializzazione JSON", e);
                }
            }
        };

        // Configura Javalin con il JsonMapper personalizzato
        Javalin app = Javalin.create(config -> config.jsonMapper(jsonMapper)).start(3050);

//        Javalin app = Javalin.create().start(3050);

        app.get("/users", UserController.getAllUsers);
        app.post("/users", UserController.createUser);
        app.put("/users/{id}", UserController.updateUser);
        app.delete("/users", UserController.deleteUser);

    }
}