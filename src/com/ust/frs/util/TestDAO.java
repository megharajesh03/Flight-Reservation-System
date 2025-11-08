package com.ust.frs.util;

import com.ust.frs.bean.*;
import com.ust.frs.dao.*;

public class TestDAO {
    public static void main(String[] args) {
        FlightDAO dao = new FlightDAO();

        FlightBean f = new FlightBean();
        f.setFlightID("AI2002");
        f.setFlightName("IndiGo Jet");
        f.setSeatingCapacity(190);
        f.setReservationCapacity(160);

        System.out.println("Add Flight: " + dao.createFlight(f));
        System.out.println("All Flights: " + dao.findAll());
    }
}
