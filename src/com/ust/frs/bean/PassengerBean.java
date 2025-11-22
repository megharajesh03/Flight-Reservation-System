package com.ust.frs.bean;

public class PassengerBean {
	private int passengerID;
	private int reservationID;
	private String name;
	private String gender;
	private int age;
	private int seatNo;

	
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "PassengerBean [reservationID=" + reservationID + ", name=" + name + ", gender=" + gender + ", age="
				+ age + ", seatNo=" + seatNo + "]";
	}
	public int getPassengerID() {
		return passengerID;
	}
	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}
	
}
