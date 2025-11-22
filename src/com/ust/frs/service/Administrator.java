package com.ust.frs.service;

import java.util.ArrayList;
import com.ust.frs.bean.*;

public interface Administrator {
    String addFlight(FlightBean flightBean);
    boolean modifyFlight(FlightBean flightBean);         
    boolean removeFlight(int flightID);                  
    FlightBean viewByFlightId(int flightId);
    ArrayList<FlightBean> viewByAllFlights(); 

    String addSchedule(ScheduleBean scheduleBean);
    boolean modifySchedule(ScheduleBean scheduleBean);
    boolean removeSchedule(int scheduleId);              
    ArrayList<ScheduleBean> viewByAllSchedule(); 
    ScheduleBean viewByScheduleId(int scheduleId); 
//    ArrayList<PassengerBean> viewPassengersByFlight(int scheduleId);
    ArrayList<PassengerBean> viewPassengersByFlight();

    String addRoute(RouteBean routeBean);
    boolean modifyRoute(RouteBean routeBean);
    boolean removeRoute(int routeId);
    RouteBean viewByRouteId(int routeId);
    ArrayList<RouteBean> viewByAllRoute();
}
