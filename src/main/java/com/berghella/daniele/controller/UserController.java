package com.berghella.daniele.controller;

import com.berghella.daniele.model.User;
import com.berghella.daniele.service.UserService;
import io.javalin.http.Handler;
import java.util.UUID;

public class UserController {

    private static UserService userService = new UserService();

    // http://localhost:3050/users (get)
    public static Handler getAllUsers = ctx -> {
        ctx.json(userService.getAllUsers());
    };

    // http://localhost:3050/users (post)
    public static Handler createUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        userService.createUser(user);
        ctx.status(201).json(user);
    };

    // http://localhost:3050/users?id: (put)
    public static Handler updateUser = ctx -> {
        UUID userId = UUID.fromString(ctx.pathParam("id"));
        User user2Update = ctx.bodyAsClass(User.class);
        if (userService.updateUser(user2Update, userId) != null) {
            ctx.status(200).json("User successfully Updated");
        } else {
            ctx.status(404).json("User not found");
        }
    };

    // http://localhost:3050/users?id:8 (delete) // queryParam
    public static Handler deleteUser = ctx -> {
        UUID userId = UUID.fromString(ctx.queryParam("id"));
        userService.deleteUser(userId);
        ctx.status(200).json("User Deleted");
    };
}
