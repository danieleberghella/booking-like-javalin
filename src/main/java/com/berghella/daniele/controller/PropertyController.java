package com.berghella.daniele.controller;

import com.berghella.daniele.model.Host;
import com.berghella.daniele.model.Property;
import com.berghella.daniele.service.HostService;
import com.berghella.daniele.service.PropertyService;
import io.javalin.http.Handler;

import java.util.UUID;

public class PropertyController {

    private static PropertyService propertyService = new PropertyService();

    // http://localhost:3050/properties (get)
    public static Handler getAllProperties = ctx -> {
        ctx.json(propertyService.getAllProperties());
    };

    // http://localhost:3050/properties (post)
    public static Handler createProperty = ctx -> {
        Property property = ctx.bodyAsClass(Property.class);
        propertyService.createProperty(property);
        ctx.status(201).json(property);
    };

    // http://localhost:3050/properties?id: (put)
    public static Handler updateProperty = ctx -> {
        UUID propertyId = UUID.fromString(ctx.pathParam("id"));
        Property property2Update = ctx.bodyAsClass(Property.class);
        if (propertyService.updateProperty(property2Update, propertyId) != null) {
            ctx.status(200).json("Property successfully Updated");
        } else {
            ctx.status(404).json("Property not found");
        }
    };

    // http://localhost:3050/properties?id:8 (delete) // queryParam
    public static Handler deleteProperty = ctx -> {
        UUID propertyId = UUID.fromString(ctx.queryParam("id"));
        propertyService.deleteProperty(propertyId);
        ctx.status(200).json("Property Deleted");
    };
}
