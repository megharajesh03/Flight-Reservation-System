package com.ust.frs.service;

import java.util.ArrayList;
import com.ust.frs.bean.*;

public interface Administrator {
    String addFlight(FlightBean flightBean);
    boolean modifyFlight(ArrayList<FlightBean> flightBeanArray);            // simpler
    boolean removeFlight(ArrayList<FlightBean> flightIDArray);                  // simpler
    String addSchedule(ScheduleBean scheduleBean);
    boolean modifySchedule(ScheduleBean scheduleBean);
    boolean removeSchedule(ArrayList<String> scheduleId);              // simpler
    String addRoute(RouteBean routeBean);
    boolean modifyRoute(RouteBean routeBean);
    boolean removeRoute(ArrayList<String> routeId);                    // simpler
    FlightBean viewByFlightId(String flightId);
    RouteBean viewByRouteId(String routeId);
    ArrayList<FlightBean> viewByAllFlights(); 
    ArrayList<RouteBean> viewByAllRoute();
    ArrayList<ScheduleBean> viewByAllSchedule(); 
    ScheduleBean viewByScheduleId(String scheduleId); 
    ArrayList<PassengerBean> viewPassengersByFlight(String scheduleId);
}
