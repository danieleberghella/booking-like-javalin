package com.berghella.daniele;


import com.berghella.daniele.controller.UserController;
import com.berghella.daniele.utility.CustomJsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;

import java.io.InputStream;


public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> config.jsonMapper(new CustomJsonMapper())).start(3050);


//        Javalin app = Javalin.create().start(3050);

        app.get("/users", UserController.getAllUsers);
        app.post("/users", UserController.createUser);
        app.put("/users/{id}", UserController.updateUser);
        app.delete("/users", UserController.deleteUser);

    }
}