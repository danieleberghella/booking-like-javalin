package com.berghella.daniele;


import com.berghella.daniele.controller.BookingController;
import com.berghella.daniele.controller.PropertyController;
import com.berghella.daniele.controller.UserController;
import com.berghella.daniele.controller.HostController;
import com.berghella.daniele.controller.FeedbackController;
import com.berghella.daniele.utility.CustomJsonMapper;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

//        Javalin app = Javalin.create(config -> config.jsonMapper(new CustomJsonMapper())).start(3050);

        DataLoader dataLoader = new DataLoader();
        dataLoader.loadData();

        Javalin app = Javalin.create().start(3050);

        // User routing
        app.get("/users", UserController.getAllUsers);
        app.post("/users", UserController.createUser);
        app.put("/users/{id}", UserController.updateUser);
        app.delete("/users", UserController.deleteUser);

        // Host routing
        app.get("/hosts", HostController.getAllHosts);
        app.post("/hosts", HostController.createHost);
        app.put("/hosts/{id}", HostController.updateHost);
        app.delete("/hosts", HostController.deleteHost);

        // Property routing
        app.get("/properties", PropertyController.getAllProperties);
        app.post("/properties", PropertyController.createProperty);
        app.put("/properties/{id}", PropertyController.updateProperty);
        app.delete("/properties", PropertyController.deleteProperty);

        // Booking routing
        app.get("/bookings", BookingController.getAllBookings);
        app.post("/bookings", BookingController.createBooking);
        app.put("/bookings/{id}", BookingController.updateBooking);
        app.delete("/bookings", BookingController.deleteBooking);

        // Feedback routing
        app.get("/feedbacks", FeedbackController.getAllFeedbacks);
        app.post("/feedbacks", FeedbackController.createFeedback);
        app.put("/feedbacks/{id}", FeedbackController.updateFeedback);
        app.delete("/feedbacks", FeedbackController.deleteFeedback);

    }
}