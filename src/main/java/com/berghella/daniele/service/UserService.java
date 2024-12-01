package com.berghella.daniele.service;

import com.berghella.daniele.dao.BookingDAO;
import com.berghella.daniele.dao.UserDAO;
import com.berghella.daniele.model.Booking;
import com.berghella.daniele.model.User;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    private final BookingDAO bookingDAO = new BookingDAO();

    public void createUser(User user) {
        userDAO.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userDAO.findById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User updateUser(User userUpdate, UUID oldUserId) {
        for (User user:userDAO.findAll()){
            if (user.getId().equals(oldUserId)) {
                return userDAO.update(userUpdate, oldUserId);
            }
        }
        return null;
    }

    public void deleteUser(UUID id) {
        userDAO.delete(id);
    }

    /* Funzionalità aggiuntive */
    public List<User> getTop5UsersWithMostBookedDaysLastMonth() {
        List<Booking> allBookings = bookingDAO.findAll();
        Map<User, Integer> userBookedDays = new HashMap<>();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        // Calcola i giorni prenotati per ciascun utente
        for (Booking booking : allBookings) {
            if (!booking.getStartDate().isBefore(oneMonthAgo)) {
                User user = booking.getUser();
                int bookedDays = (int) DAYS.between(booking.getStartDate(), booking.getEndDate());
                if (!userBookedDays.containsKey(user)) {
                    userBookedDays.put(user, 0);
                }
                userBookedDays.put(user, userBookedDays.get(user) + bookedDays);
            }
        }

        // Ordina gli utenti in base ai giorni prenotati
        List<Map.Entry<User, Integer>> sortedEntries = new ArrayList<>();
        for (Map.Entry<User, Integer> entry : userBookedDays.entrySet()) {
            sortedEntries.add(entry);
        }

        for (int i = 0; i < sortedEntries.size() - 1; i++) {
            for (int j = i + 1; j < sortedEntries.size(); j++) {
                if (sortedEntries.get(i).getValue() < sortedEntries.get(j).getValue()) {
                    Map.Entry<User, Integer> temp = sortedEntries.get(i);
                    sortedEntries.set(i, sortedEntries.get(j));
                    sortedEntries.set(j, temp);
                }
            }
        }

        // Seleziona i primi 5 utenti
        List<User> top5Users = new ArrayList<>();
        for (int i = 0; i < Math.min(5, sortedEntries.size()); i++) {
            top5Users.add(sortedEntries.get(i).getKey());
        }

        return top5Users;
    }

}

