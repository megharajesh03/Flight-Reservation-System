package com.ust.frs.dao;

import java.util.ArrayList;

import com.ust.frs.bean.FlightBean;
import com.ust.frs.bean.PassengerBean;
import com.ust.frs.bean.RouteBean;
import com.ust.frs.bean.ScheduleBean;
import com.ust.frs.service.Administrator;

public class AdministratorDAO implements Administrator{

	@Override
	public String addFlight(FlightBean flightBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyFlight(FlightBean flightBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeFlight(ArrayList<String> flightID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String addSchedule(ScheduleBean scheduleBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifySchedule(ScheduleBean scheduleBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeSchedule(ArrayList<String> scheduleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String addRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyRoute(RouteBean routeBean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeRoute(ArrayList<String> routeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FlightBean viewByFlightId(String flightId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteBean viewByRouteId(String routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FlightBean> viewByAllFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RouteBean> viewByAllRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ScheduleBean> viewByAllSchedule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleBean viewByScheduleId(String scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PassengerBean> viewPassengersByFlight(String scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
