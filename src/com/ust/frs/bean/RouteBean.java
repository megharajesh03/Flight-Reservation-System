package com.ust.frs.bean;

public class RouteBean {
	private int routeID;
	private String source;
	private String destination;
	private int distance;
	private double fare;
	
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	@Override
	public String toString() {
		return "RouteBean [routeID=" + routeID + ", source=" + source + ", destination=" + destination + ", distance="
				+ distance + ", fare=" + fare + "]";
	}
	
}
