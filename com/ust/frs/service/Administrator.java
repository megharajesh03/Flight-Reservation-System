package com.ust.frs.service;

import java.util.ArrayList;

import com.ust.frs.bean.FlightBean;
import com.ust.frs.bean.PassengerBean;
import com.ust.frs.bean.RouteBean;
import com.ust.frs.bean.ScheduleBean;

public interface Administrator {
	public String addFlight(FlightBean flightBean);
	public boolean modifyFlight(ArrayList<FlightBean> flightBeanArray);
	public int removeFlight(ArrayList<FlightBean> flightID);
	public String addSchedule(ScheduleBean scheduleBean);
	boolean modifySchedule(ScheduleBean scheduleBean);
	int removeSchedule(ArrayList<String> scheduleId);
	String addRoute(RouteBean routeBean);
	boolean modifyRoute(RouteBean routeBean);
	int removeRoute(ArrayList<String> routeId);
	FlightBean viewByFlightId(String flightId);
	RouteBean viewByRouteId(String routeId);
	ArrayList<FlightBean> viewByAllFlights(); 
	ArrayList<RouteBean> viewByAllRoute();
	ArrayList<ScheduleBean> viewByAllSchedule(); 
	ScheduleBean viewByScheduleId(String scheduleId); 
	ArrayList<PassengerBean> viewPassengersByFlight(String scheduleId);
}
