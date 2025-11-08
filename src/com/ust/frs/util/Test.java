/*
package com.ust.frs.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.ust.frs.dao.*;

import com.ust.frs.bean.FlightBean;
import com.ust.frs.bean.PassengerBean;
import com.ust.frs.bean.ProfileBean;
import com.ust.frs.bean.ReservationBean;
import com.ust.frs.bean.RouteBean;

public class Test {
	//@SuppressWarnings("all")
	public static void main(String[] args) {
		AdministratorDAO admin = new AdministratorDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Login ID: ");
		String LoginID = scanner.nextLine();
		System.out.print("Enter password: ");
		String pswd = scanner.nextLine();

		if (LoginID.equals("Admin1") && pswd.equals("0000")) {
			while(true) {
				System.out.print("Enter choice: (AD-001 to AD-014)");
				String choice = scanner.nextLine();
				
				switch (choice) {
				case "AD-001":
					//Add flight
					FlightBean fb1 = new FlightBean();
					
					System.out.print("Add Flight ID:");
					String flightid = scanner.nextLine();
					fb1.setFlightID(flightid);
//					flightids.add(flightid);
					System.out.print("Add Flight name:");
					String flightname = scanner.nextLine();
					fb1.setFlightName(flightname);
					System.out.print("Add Seating Capacity:");
					int SeatCapa = scanner.nextInt();
					fb1.setSeatingCapacity(SeatCapa);
					System.out.print("Add Reservation Capacity:");
					int ReserveCapa = scanner.nextInt();
					fb1.setReservationCapacity(ReserveCapa);
					
					String res = admin.addFlight(fb1);
					System.out.println(res);
					break;
					
				case "AD-002":
					//REMOVAL
					int res1 = admin.removeFlight(flightBeanArray);
					
					if (res1==1) {
					    System.out.println("Flight removed successfully.");
					} else if(res1==0){
					    System.out.println("Flight ID not found.");
					}else {
						System.out.println("Error");
					}
					break;
					
				case "AD-003":
					//VIEW
					System.out.println("Veiwing All Flights");
					Iterator itr2 = flightBeanArray.iterator();
					while(itr2.hasNext()) {
						System.out.println(itr2.next());
					}
					break;
					
				case "AD-004":
					//MODIFY
					boolean res2 = admin.modifyFlight(flightBeanArray);
					if (res2==true) {
					    System.out.println("Flight modified successfully.");
					} else if(res2==false){
					    System.out.println("Flight ID not found.");
					}else {
						System.out.println("Error");
					}
					break;
				case "AD-005":
					
					break;
				case "AD-006":
					
					break;	
				case "AD-007":
					
					break;
				case "AD-008":
					
					break;	
				case "AD-009":
					
					break;
				case "AD-010":
					
					break;	
				case "AD-011":
					
					break;
				case "AD-012":
					
					break;
				case "AD-013":
					
					break;
				case "AD-014":
					System.out.println("Exiting...");
					System.exit(0);
				}//switch
			}//while
		}//if
		else {
			System.out.println("Wrong login id (or) password");
		}
		
	}
	public static ArrayList<FlightBean> flightBeanArray = new ArrayList<FlightBean>();
//	public static ArrayList<String> flightids=new ArrayList<String>();
	public static ArrayList<PassengerBean> passengerbean = new ArrayList<PassengerBean>();
	public static ArrayList<ProfileBean> profilebean = new ArrayList<ProfileBean>();
	public static ArrayList<ReservationBean> reserversationbean = new ArrayList<ReservationBean>();
	public static ArrayList<RouteBean> routebean = new ArrayList<RouteBean>();
	
}
*/

