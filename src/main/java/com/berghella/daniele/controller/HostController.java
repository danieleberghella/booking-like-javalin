package com.berghella.daniele.controller;

import com.berghella.daniele.model.Host;
import com.berghella.daniele.model.User;
import com.berghella.daniele.service.HostService;
import com.berghella.daniele.service.UserService;
import io.javalin.http.Handler;

import java.util.UUID;

public class HostController {

    private static HostService hostService = new HostService();

    // http://localhost:3050/hosts (get)
    public static Handler getAllHosts = ctx -> {
        ctx.json(hostService.getAllHosts());
    };

    // http://localhost:3050/hosts (post)
    public static Handler createHost = ctx -> {
        Host user = ctx.bodyAsClass(Host.class);
        hostService.createHost(user);
        ctx.status(201).json(user);
    };

    // http://localhost:3050/hosts?id: (put)
    public static Handler updateHost = ctx -> {
        UUID userId = UUID.fromString(ctx.pathParam("id"));
        Host user2Update = ctx.bodyAsClass(Host.class);
        if (hostService.updateHost(user2Update, userId) != null) {
            ctx.status(200).json("User successfully Updated");
        } else {
            ctx.status(404).json("User not found");
        }
    };

    // http://localhost:3050/hosts?id:8 (delete) // queryParam
    public static Handler deleteHost = ctx -> {
        UUID userId = UUID.fromString(ctx.queryParam("id"));
        hostService.deleteHost(userId);
        ctx.status(200).json("Host Deleted");
    };
}
