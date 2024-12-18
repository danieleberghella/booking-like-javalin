package com.berghella.daniele.controller;

import com.berghella.daniele.model.User;
import com.berghella.daniele.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserController {

    private static UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void registerRoutes(Javalin app) {
        // http://localhost:3050/users
        app.post("/users", this::createUser);
        app.get("/users", this::getAllUsers);
        app.get("/users/{id}", this::getUserById);
        app.put("/users/{id}", this::updateUser);
        app.delete("/users", this::deleteUser);
        app.get("/users/top-5-users-last-month", this::getTop5UsersWithMostBookedDaysLastMonth);
    }

    public void createUser(Context ctx) {
        try {
            User user2Add = ctx.bodyAsClass(User.class);
            userService.createUser(user2Add);
            ctx.status(HttpStatus.CREATED).json(user2Add.getId());
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Invalid request");
        }
    }

    public void getAllUsers(Context ctx) {
        try {
            List<User> users = userService.getAllUsers();
            if (!users.isEmpty()) {
                ctx.status(HttpStatus.OK).json(users);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).json("Users not found");
            }
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).result("Invalid request");
        }
    }

    public void getUserById(Context ctx) {
        Optional<User> user;
        try {
            UUID id = UUID.fromString(ctx.pathParam("id"));
            user = userService.getUserById(id);
            if (user.isPresent()) {
                ctx.status(HttpStatus.OK).json(user.get());
            } else {
                ctx.status(HttpStatus.NOT_FOUND).json("User not found");
            }
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).json("Invalid request");
        }
    }

    public void updateUser(Context ctx) {
        try {
            UUID userId = UUID.fromString(ctx.pathParam("id"));
            User userFormUpdate = ctx.bodyAsClass(User.class);
            User userUpdated = userService.updateUser(userFormUpdate, userId);
            if (userUpdated != null) {
                ctx.status(HttpStatus.OK).json(userUpdated);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).json("User not found");
            }
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).json("Invalid request");
        }
    }

    public void deleteUser(Context ctx) {
        UUID userId = UUID.fromString(ctx.queryParam("id"));
        userService.deleteUser(userId);
        ctx.status(200).json("User Deleted");
    }

    public void getTop5UsersWithMostBookedDaysLastMonth(Context ctx) {
        try {
            List<User> top5Users = userService.getTop5UsersWithMostBookedDaysLastMonth();
            if (!top5Users.isEmpty()) {
                ctx.status(HttpStatus.OK).json(top5Users);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).json("Users not found");
            }
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST).json("Invalid request");
        }
    }
}
