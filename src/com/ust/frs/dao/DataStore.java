package com.ust.frs.dao;

import java.util.*;
import com.ust.frs.bean.*;

public class DataStore {

    public static List<FlightBean> flights = new ArrayList<>();
    public static List<RouteBean> routes = new ArrayList<>();
    public static List<ScheduleBean> schedules = new ArrayList<>();
    public static List<ReservationBean> reservations = new ArrayList<>();
    public static List<CredentialsBean> users = new ArrayList<>();


    // Add some sample data so you can test immediately
    static {
        FlightBean f1 = new FlightBean();
        f1.setFlightID("AI1001");
        f1.setFlightName("AirIndia Express");
        f1.setSeatingCapacity(180);
        f1.setReservationCapacity(150);
        flights.add(f1);

        RouteBean r1 = new RouteBean();
        r1.setRouteID("CHDE1001");
        r1.setSource("Chennai");
        r1.setDestination("Delhi");
        r1.setDistance(1800);
        r1.setFare(6.5);
        routes.add(r1);
        
        // Mock users
        CredentialsBean admin = new CredentialsBean();
        admin.setUserID("admin01");
        admin.setPassword("admin123");
        admin.setUserType("A");
        admin.setLoginStatus(0);

        CredentialsBean cust = new CredentialsBean();
        cust.setUserID("user01");
        cust.setPassword("user123");
        cust.setUserType("C");
        cust.setLoginStatus(0);

        users.add(admin);
        users.add(cust);

    }
}
