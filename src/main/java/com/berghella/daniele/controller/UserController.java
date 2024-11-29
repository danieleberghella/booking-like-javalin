package com.berghella.daniele.controller;

import com.berghella.daniele.model.User;
import com.berghella.daniele.service.UserService;
import io.javalin.http.Handler;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserController {

    private static UserService userService = new UserService();

    // http://localhost:8000/users (get)
    public static Handler getAllUsers = ctx -> {
        ctx.json(userService.getAllUsers());
    };

    // http://localhost:8000/users (post)
    public static Handler createUser = ctx -> {

        System.out.println(ctx.body());
        JSONObject jsonObject;
//        User user = new User();
//        try {
//            jsonObject = new JSONObject(ctx.body());
//            for (String k: jsonObject.keySet()) {
//                = (jsonObject);
//            }
//        }catch (JSONException err){
//            System.out.println("Error: " + err);
//        }
        // TODO: Ciclo il json e creo l'user con i valori

        User user = ctx.bodyAsClass(User.class);
        userService.createUser(user);
        ctx.status(201).json(user);
    };

    // http://localhost:8000/users/5 (put)
    public static Handler updateUser = ctx -> {

        UUID userId = UUID.fromString(ctx.pathParam("id"));

        User user2Update = ctx.bodyAsClass(User.class);

        if (userService.updateUser(user2Update, userId) != null) {
            ctx.status(200).json("User successfully Updated");
        } else {
            ctx.status(404).json("User not found");
        }
    };

    // http://localhost:8000/users?id:8 (delelte) // queryParam
    public static Handler deleteUser = ctx -> {
        UUID userId = UUID.fromString(ctx.queryParam("id"));
        userService.deleteUser(userId);
        ctx.status(200).json("User Deleted");
    };
}
