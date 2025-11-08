package com.ust.frs.dao;

import java.util.*;
import com.ust.frs.bean.*;

public class FlightDAO {

    public String createFlight(FlightBean flight) {
        // Check if exists
        for (FlightBean f : DataStore.flights) {
            if (f.getFlightID().equalsIgnoreCase(flight.getFlightID())) {
                return "FAIL"; // Duplicate
            }
        }
        DataStore.flights.add(flight);
        return "SUCCESS";
    }

    public boolean updateFlight(FlightBean flight) {
        for (int i = 0; i < DataStore.flights.size(); i++) {
            if (DataStore.flights.get(i).getFlightID().equalsIgnoreCase(flight.getFlightID())) {
                DataStore.flights.set(i, flight);
                return true;
            }
        }
        return false;
    }

    public boolean deleteFlight(String flightID) {
        return DataStore.flights.removeIf(f -> f.getFlightID().equalsIgnoreCase(flightID));
    }

    public FlightBean findById(String flightID) {
        for (FlightBean f : DataStore.flights) {
            if (f.getFlightID().equalsIgnoreCase(flightID)) {
                return f;
            }
        }
        return null;
    }

    public List<FlightBean> findAll() {
        return new ArrayList<>(DataStore.flights);
    }
}
