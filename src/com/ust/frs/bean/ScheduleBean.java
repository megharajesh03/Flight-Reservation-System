package com.ust.frs.bean;

public class ScheduleBean {
	private String scheduleID;
	private String flightID;
	private String routeID;
	private int travelDuration;
	private String availableDays;
	private String departureTime;
	
	public ScheduleBean(String scheduleID, String flightID, String routeID, int travelDuration, String availableDays,
			String departureTime) {
		super();
		this.scheduleID = scheduleID;
		this.flightID = flightID;
		this.routeID = routeID;
		this.travelDuration = travelDuration;
		this.availableDays = availableDays;
		this.departureTime = departureTime;
	}
	public String getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public String getRouteID() {
		return routeID;
	}
	public void setRouteID(String routeID) {
		this.routeID = routeID;
	}
	public int getTravelDuration() {
		return travelDuration;
	}
	public void setTravelDuration(int travelDuration) {
		this.travelDuration = travelDuration;
	}
	public String getAvailableDays() {
		return availableDays;
	}
	public void setAvailableDays(String availableDays) {
		this.availableDays = availableDays;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
}
