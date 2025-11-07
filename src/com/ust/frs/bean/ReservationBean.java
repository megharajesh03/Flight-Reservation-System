package com.ust.frs.bean;

public class ReservationBean {
	private String reservationID;
	private String userID;
	private String scheduleID;
	private String reservationType;
	private String bookingDate;
	private String journeyDate;
	private int noOfSeats;
	private double totalFare;
	private int bookingStatus;
	
	public ReservationBean(String reservationID, String userID, String scheduleID, String reservationType,
			String bookingDate, String journeyDate, int noOfSeats, double totalFare, int bookingStatus) {
		super();
		this.reservationID = reservationID;
		this.userID = userID;
		this.scheduleID = scheduleID;
		this.reservationType = reservationType;
		this.bookingDate = bookingDate;
		this.journeyDate = journeyDate;
		this.noOfSeats = noOfSeats;
		this.totalFare = totalFare;
		this.bookingStatus = bookingStatus;
	}
	public String getReservationID() {
		return reservationID;
	}
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public int getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
}
