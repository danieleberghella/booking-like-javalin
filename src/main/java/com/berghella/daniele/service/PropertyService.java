package com.berghella.daniele.service;

import com.berghella.daniele.dao.BookingDAO;
import com.berghella.daniele.dao.PropertyDAO;
import com.berghella.daniele.model.Booking;
import com.berghella.daniele.model.Host;
import com.berghella.daniele.model.Property;
import com.berghella.daniele.model.User;

import java.time.LocalDate;
import java.util.*;

public class PropertyService {
    private final PropertyDAO propertyDAO = new PropertyDAO();
    private final BookingDAO bookingDAO = new BookingDAO();

    public void createProperty(Property property) {
        propertyDAO.save(property);
    }

    public Optional<Property> getPropertyById(UUID id) {
        return propertyDAO.findById(id);
    }

    public List<Property> getAllProperties() {
        return propertyDAO.findAll();
    }

    public Property updateProperty(Property propertyUpdate, UUID oldPropertyId) {
        for (Property property:propertyDAO.findAll()){
            if (property.getId().equals(oldPropertyId)) {
                return propertyDAO.update(propertyUpdate, oldPropertyId);
            }
        }
        return null;
    }

    public void deleteProperty(UUID id) {
        propertyDAO.delete(id);
    }

    /* Funzionalità aggiuntive */
    public List<Property> getPropertiesByHostCode(String hostCode) {
        List<Booking> allBookings = bookingDAO.findAll();
        List<Property> propertiesByHost = new ArrayList<>();

        for (Booking booking : allBookings) {
            User user = booking.getUser();
            if (user instanceof Host && ((Host) user).getCode().equals(hostCode)) {
                Property property = booking.getProperty();
                if (!propertiesByHost.contains(property)) {
                    propertiesByHost.add(property);
                }
            }
        }
        return propertiesByHost;
    }

    public Property getMostPopularPropertyLastMonth() {
        List<Booking> allBookings = bookingDAO.findAll();
        Map<Property, Integer> propertyBookingCount = new HashMap<>();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        for (Booking booking : allBookings) {
            if (!booking.getStartDate().isBefore(oneMonthAgo)) {
                Property property = booking.getProperty();
                if (!propertyBookingCount.containsKey(property)) {
                    propertyBookingCount.put(property, 0);
                }
                propertyBookingCount.put(property, propertyBookingCount.get(property) + 1);
            }
        }

        Property mostPopularProperty = null;
        int maxCount = 0;
        for (Map.Entry<Property, Integer> entry : propertyBookingCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopularProperty = entry.getKey();
            }
        }

        return mostPopularProperty;
    }

    public double getAverageBedsInProperties() {
        List<Property> allProperties = propertyDAO.findAll();
        if (allProperties.isEmpty()) {
            return 0.0;
        }

        int totalBeds = 0;
        for (Property property : allProperties) {
            totalBeds += property.getBeds();
        }

        return (double) totalBeds / allProperties.size();
    }

}

