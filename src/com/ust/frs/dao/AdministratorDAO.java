package com.ust.frs.dao;

import java.util.*;
import com.ust.frs.bean.*;

public class AdministratorDAO {

    public String addFlight(FlightBean flightBean) {
        for (FlightBean f : DataStore.flights) {
            if (f.getFlightID().equalsIgnoreCase(flightBean.getFlightID())) {
                return "FAIL"; // duplicate flight
            }
        }
        DataStore.flights.add(flightBean);
        return "SUCCESS";
    }

    public boolean modifyFlight(FlightBean flightBean) {
        for (int i = 0; i < DataStore.flights.size(); i++) {
            if (DataStore.flights.get(i).getFlightID().equalsIgnoreCase(flightBean.getFlightID())) {
                DataStore.flights.set(i, flightBean);
                return true;
            }
        }
        return false;
    }

    public boolean removeFlight(String flightId) {
        return DataStore.flights.removeIf(f -> f.getFlightID().equalsIgnoreCase(flightId));
    }

    public FlightBean viewByFlightId(String flightId) {
        for (FlightBean f : DataStore.flights) {
            if (f.getFlightID().equalsIgnoreCase(flightId)) {
                return f;
            }
        }
        return null;
    }

    public ArrayList<FlightBean> viewAllFlights() {
        return new ArrayList<>(DataStore.flights);
    }

    // You can add similar methods for Routes and Schedules later
}
