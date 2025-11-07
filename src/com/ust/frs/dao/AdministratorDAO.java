package com.ust.frs.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.ust.frs.bean.FlightBean;
import com.ust.frs.bean.PassengerBean;
import com.ust.frs.bean.RouteBean;
import com.ust.frs.bean.ScheduleBean;
import com.ust.frs.service.Administrator;
import com.ust.frs.util.Test;

public class AdministratorDAO implements Administrator{

	private ArrayList<FlightBean> flights=new ArrayList<FlightBean>();
	
	@Override
	public String addFlight(FlightBean flightBean) {
		String result= (Test.flightBeanArray.add(flightBean))?"Success":"Fail";
		return result;
	}

	@Override
	public boolean modifyFlight(ArrayList<FlightBean> flightBeanArray) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter flight ID to modify: ");
		String FlightId = scanner.nextLine();
		if (FlightId=="") {return false;}
		for (FlightBean flight : flightBeanArray) {
			if (flight.getFlightID().equals(FlightId)) {
				System.out.print("Enter new flight name (Skip if no change): ");
				String newName = scanner.nextLine();
				System.out.print("Enter new Capacity: ");
				int newCapacity = scanner.nextInt();
				System.out.print("Enter new Reservation capacity : ");
				int newReservedSeats = scanner.nextInt();
				if (newReservedSeats > newCapacity) {
					System.out.println("Error: Reserved Seats can't be higher than capacity.");
				}
				if (newName != "") {
					flight.setFlightName(newName);
				}
	//	       	if (newReservedSeats != null) {
		           flight.setReservationCapacity(newReservedSeats);
	//	       	}
	//	       	if (newCapacity!=null) {
		           flight.setSeatingCapacity(newCapacity);
	//	       	}
		        return true; // Update successful
			}
		}
		return false;// Flight not found
	}


	@Override
	public int removeFlight(ArrayList<FlightBean> flightID) {
		Scanner scanner = new Scanner(System.in);			//
		System.out.print("Enter flight ID to remove: ");	//
		String inputFlightId = scanner.nextLine();			//
		boolean removed = Test.flightBeanArray.removeIf(flight -> flight.getFlightID().equals(inputFlightId));
		if (removed) {
			return 1;
		} else {
		    return 0;
		}
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
		FlightBean f=new FlightBean();
		for(FlightBean flight:Test.flightBeanArray)
		{
			if(flight.getFlightID().equals(flightId))
			{
				f=flight;
			}
		}
		return f;
	}

	@Override
	public RouteBean viewByRouteId(String routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FlightBean> viewByAllFlights() {
		return Test.flightBeanArray;
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
