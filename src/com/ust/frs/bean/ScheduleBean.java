package com.ust.frs.bean;

public class ScheduleBean {
	private int scheduleID;
	private int flightID;
	private int routeID;
	private int travelDuration;
	private String availableDays;
	private String departureTime;
	
	public int getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
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
	@Override
	public String toString() {
		return "ScheduleBean [scheduleID=" + scheduleID + ", flightID=" + flightID + ", routeID=" + routeID
				+ ", travelDuration=" + travelDuration + ", availableDays=" + availableDays + ", departureTime="
				+ departureTime + "]";
	}
	
	
}
