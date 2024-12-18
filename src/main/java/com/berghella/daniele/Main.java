package com.berghella.daniele;


import com.berghella.daniele.controller.BookingController;
import com.berghella.daniele.controller.PropertyController;
import com.berghella.daniele.controller.UserController;
import com.berghella.daniele.controller.HostController;
import com.berghella.daniele.controller.FeedbackController;
import com.berghella.daniele.service.UserService;
import com.berghella.daniele.utility.CustomJsonMapper;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

//        Javalin app = Javalin.create(config -> config.jsonMapper(new CustomJsonMapper())).start(3050);

//        DataLoader dataLoader = new DataLoader();
//        dataLoader.loadData();

        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
//                    it.allowHost("example.com", "javalin.io");
                    it.anyHost();
                });
            });
        }).start(3050);

        // Inizializza i servizi e i controller
        UserService userService = new UserService();
        UserController userController = new UserController(userService);
//        AuthController authController = new AuthController();

        // User routing
        userController.registerRoutes(app);
//        authController.registerRoutes(app);




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
        app.get("/properties/3-most-popular", PropertyController.get3MostPopularPropertiesLastMonth);

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