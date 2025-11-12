package com.ust.frs.service;

import java.util.ArrayList;
import com.ust.frs.bean.*;

public interface Administrator {
    String addFlight(FlightBean flightBean);
    boolean modifyFlight(FlightBean flightBean);            // simpler
    boolean removeFlight(int flightID);                  // simpler
    FlightBean viewByFlightId(int flightId);
    ArrayList<FlightBean> viewByAllFlights(); 

    String addSchedule(ScheduleBean scheduleBean);
    boolean modifySchedule(ScheduleBean scheduleBean);
    boolean removeSchedule(int scheduleId);              // simpler
    ArrayList<ScheduleBean> viewByAllSchedule(); 
    ScheduleBean viewByScheduleId(int scheduleId); 
    ArrayList<PassengerBean> viewPassengersByFlight(String scheduleId);

    String addRoute(RouteBean routeBean);
    boolean modifyRoute(RouteBean routeBean);
    boolean removeRoute(int routeId);                    // simpler
    RouteBean viewByRouteId(int routeId);
    ArrayList<RouteBean> viewByAllRoute();
}
