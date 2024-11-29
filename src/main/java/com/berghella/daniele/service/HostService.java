package com.berghella.daniele.service;

import com.berghella.daniele.dao.BookingDAO;
import com.berghella.daniele.dao.HostDAO;
import com.berghella.daniele.dao.UserDAO;
import com.berghella.daniele.model.Booking;
import com.berghella.daniele.model.Host;
import com.berghella.daniele.model.User;

import java.time.LocalDate;
import java.util.*;

public class HostService {
    private final HostDAO hostDAO = new HostDAO();
    private final BookingDAO bookingDAO = new BookingDAO();
    private final UserDAO userDAO = new UserDAO();

    public void createHost(Host host) {
        hostDAO.save(host);
    }

    public Optional<Host> getHostById(UUID id) {
        return hostDAO.findById(id);
    }

    public List<Host> getAllHosts() {
        return hostDAO.findAll();
    }

    public void deleteHost(UUID id) {
        hostDAO.delete(id);
    }

    /* Funzionalità aggiuntive */
    public List<Host> getHostsWithMostBookingsLastMonth() {
        List<Booking> allBookings = bookingDAO.findAll();
        Map<Host, Integer> hostBookingCount = new HashMap<>();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        for (Booking booking : allBookings) {
            if (!booking.getStartDate().isBefore(oneMonthAgo)) {
                User user = booking.getUser();
                if (user instanceof Host) {
                    Host host = (Host) user;
                    if (!hostBookingCount.containsKey(host)) {
                        hostBookingCount.put(host, 0);
                    }
                    hostBookingCount.put(host, hostBookingCount.get(host) + 1);
                }
            }
        }

        List<Host> topHosts = new ArrayList<>();
        int maxBookings = 0;

        for (Integer bookings : hostBookingCount.values()) {
            if (bookings > maxBookings) {
                maxBookings = bookings;
            }
        }

        for (Map.Entry<Host, Integer> entry : hostBookingCount.entrySet()) {
            if (entry.getValue() == maxBookings) {
                topHosts.add(entry.getKey());
            }
        }

        return topHosts;
    }

    public List<Host> getAllSuperHosts() {
        List<User> allUsers = userDAO.findAll();
        List<Host> superHosts = new ArrayList<>();

        for (User user : allUsers) {
            if (user instanceof Host) {
                Host host = (Host) user;

                if (host.getCode() != null && !host.getCode().isEmpty()) {
                    superHosts.add(host);
                }
            }
        }

        return superHosts;
    }

}