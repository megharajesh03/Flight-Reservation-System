package com.ust.frs.util;
//import com.ust.frs.util.Authentication;
//import com.ust.frs.util.AuthenticationImpl;

import java.util.*;
import com.ust.frs.bean.*;
import com.ust.frs.service.*;

import com.ust.frs.bean.CredentialsBean;
import com.ust.frs.dao.DataStore;


public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Administrator adminService = new AdministratorImpl();
    private static Customer customerService = new CustomerImpl();
    private static Authentication authService = new AuthenticationImpl();


    public static void main(String[] args) {
        System.out.println("========== Flight Reservation System ==========");
        boolean running = true;

        while (running) {
            System.out.println("\n1. Login");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter User ID: ");
                    String userId = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    CredentialsBean creds = new CredentialsBean();
                    creds.setUserID(userId);
                    creds.setPassword(password);

                    boolean isValid = authService.authenticate(creds);

                    if (isValid) {
                        // Find user type
                        String userType = null;
                        for (CredentialsBean u : DataStore.users) {
                            if (u.getUserID().equals(userId)) {
                                userType = u.getUserType();
                            }
                        }

                        authService.changeLoginStatus(creds, 1);
                        if ("A".equalsIgnoreCase(userType)) {
                            System.out.println("\nWelcome Administrator!");
                            adminMenu();
                        } else if ("C".equalsIgnoreCase(userType)) {
                            System.out.println("\nWelcome Customer!");
                            customerMenu();
                        } else {
                            System.out.println("Unknown user type.");
                        }

                    } else {
                        System.out.println("Invalid credentials! Try again.");
                    }
                }
                case 0 -> {
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }

    }

    /* ---------------- ADMIN MENU ---------------- */
    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n---- Administrator Menu ----");
            System.out.println("AD-001: Add Flight");
            System.out.println("AD-002: View All Flights");
            System.out.println("AD-003: Delete Flight");
            System.out.println("AD-004: Modify Flight");
            System.out.println("AD-005: Add Route");
            System.out.println("AD-006: View All Routes");
            System.out.println("AD-007: Delete Route");
            System.out.println("AD-008: Modify Route");
            System.out.println("AD-009: Add Schedule");
            System.out.println("AD-010: View All Schedules");
            System.out.println("AD-011: Delete Schedule");
            System.out.println("AD-012: Modify Schedule");
//          System.out.println("AD-013: View Passenger");
            System.out.println("AD-000: Back");
            System.out.print("Choice: ");
            String ch = sc.nextLine();

            switch (ch) {
	            case "AD-001" -> addFlight();
	            case "AD-002" -> viewFlights();
	            case "AD-003" -> deleteFlight();
	            case "AD-004" -> modifyFlight();
	            case "AD-005" -> addRoute();
	            case "AD-006" -> viewRoutes();
	            case "AD-007" -> deleteRoute();
	            case "AD-008" -> modifyRoute();
	            case "AD-009" -> addSchedule();
	            case "AD-010" -> viewSchedules();
	            case "AD-011" -> deleteSchedule();
	            case "AD-012" -> modifySchedule();
//	            case "AD-013" -> viewPassenger();
	            case "AD-000" -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }


    /* ---------------- CUSTOMER MENU ---------------- */
    private static void customerMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n---- Customer Menu ----");
//			System.out.println("US-001: Register User Profile");
            System.out.println("US-002: View Schedule by Route");
            System.out.println("US-003: Reserve Ticket");
            System.out.println("US-004: Cancel Ticket");
            System.out.println("US-005: View Ticket");
            System.out.println("US-000: Back");
            System.out.print("Choice: ");
            String ch = sc.nextLine();

            switch (ch) {
//              case "US-001" -> RegisterUser();
                case "US-002" -> viewScheduleByRoute();
                case "US-003" -> reserveTicket();
                case "US-004" -> cancelTicket();
                case "US-005" -> viewTicket();
                case "US-000" -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    /* ---------------- ADMIN FUNCTIONS ---------------- */

    private static void addFlight() {
        FlightBean f = new FlightBean();
        System.out.print("Enter Flight ID: ");
        f.setFlightID(sc.nextLine());
        System.out.print("Enter Flight Name: ");
        f.setFlightName(sc.nextLine());
        System.out.print("Enter Seating Capacity: ");
        f.setSeatingCapacity(sc.nextInt());
        System.out.print("Enter Reservation Capacity: ");
        f.setReservationCapacity(sc.nextInt());
        sc.nextLine();

        String result = adminService.addFlight(f);
        System.out.println("Add Flight Result: " + result);
    }

    private static void viewFlights() {
        ArrayList<FlightBean> flights = adminService.viewByAllFlights();
        if (flights.isEmpty()) System.out.println("No flights available!");
        else flights.forEach(System.out::println);
    }

    private static void deleteFlight() {
        System.out.print("Enter Flight ID to delete: ");
        String id = sc.nextLine();
        ArrayList<FlightBean> list = new ArrayList<>();
        FlightBean fb = new FlightBean();
        fb.setFlightID(id);
        list.add(fb);
        boolean deleted = adminService.removeFlight(list);
        System.out.println(deleted == true ? "Flight deleted." : "Flight not found.");
    }
    private static void modifyFlight() {
        System.out.print("Enter Flight ID to modify: ");
        String flightId = sc.nextLine();
        FlightBean existing = adminService.viewByFlightId(flightId);

        if (existing == null) {
            System.out.println("Flight not found!");
            return;
        }

        System.out.print("Enter new Flight Name (leave blank to keep current): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) existing.setFlightName(newName);

        System.out.print("Enter new Seating Capacity (leave blank to keep current): ");
        String newSeatStr = sc.nextLine();
        if (!newSeatStr.isEmpty()) {
            int newSeats = Integer.parseInt(newSeatStr);
            existing.setSeatingCapacity(newSeats);
        }

        System.out.print("Enter new Reservation Capacity (leave blank to keep current): ");
        String newResStr = sc.nextLine();
        if (!newResStr.isEmpty()) {
            int newRes = Integer.parseInt(newResStr);
            existing.setReservationCapacity(newRes);
        }

        ArrayList<FlightBean> list = new ArrayList<>();
        list.add(existing);
        boolean updated = adminService.modifyFlight(list);

        System.out.println(updated ? "Flight updated successfully!" : "Update failed.");
    }
    
    /* ---------------- ROUTE FUNCTIONS ---------------- */

    private static void addRoute() {
        RouteBean r = new RouteBean();
        System.out.print("Enter Route ID: ");
        r.setRouteID(sc.nextLine());
        System.out.print("Enter Source: ");
        r.setSource(sc.nextLine());
        System.out.print("Enter Destination: ");
        r.setDestination(sc.nextLine());
        System.out.print("Enter Distance: ");
        r.setDistance(sc.nextInt());
        System.out.print("Enter Fare: ");
        r.setFare(sc.nextDouble());
        sc.nextLine();

        String result = adminService.addRoute(r);
        System.out.println("Add Route Result: " + result);
    }

    private static void viewRoutes() {
        ArrayList<RouteBean> routes = adminService.viewByAllRoute();
        if (routes.isEmpty()) System.out.println("No routes available!");
        else routes.forEach(System.out::println);
    }
    private static void deleteRoute() {
        System.out.print("Enter Route ID to delete: ");
        String id = sc.nextLine();
        ArrayList<String> ids = new ArrayList<>();
        ids.add(id);
        boolean deleted = adminService.removeRoute(ids);
        System.out.println(deleted ? "Route deleted successfully!" : "Route not found!");
    }
    private static void modifyRoute() {
        System.out.print("Enter Route ID to modify: ");
        String routeId = sc.nextLine();
        RouteBean existing = adminService.viewByRouteId(routeId);

        if (existing == null) {
            System.out.println("Route not found!");
            return;
        }

        System.out.print("Enter new Source (leave blank to keep current): ");
        String newSource = sc.nextLine();
        if (!newSource.isEmpty()) existing.setSource(newSource);

        System.out.print("Enter new Destination (leave blank to keep current): ");
        String newDest = sc.nextLine();
        if (!newDest.isEmpty()) existing.setDestination(newDest);

        System.out.print("Enter new Distance (leave blank to keep current): ");
        String newDistStr = sc.nextLine();
        if (!newDistStr.isEmpty()) {
            int newDist = Integer.parseInt(newDistStr);
            existing.setDistance(newDist);
        }

        System.out.print("Enter new Fare (leave blank to keep current): ");
        String newFareStr = sc.nextLine();
        if (!newFareStr.isEmpty()) {
            double newFare = Double.parseDouble(newFareStr);
            existing.setFare(newFare);
        }

        boolean updated = adminService.modifyRoute(existing);
        System.out.println(updated ? "Route updated successfully!" : "Update failed.");
    }

    /* ---------------- SCHEDULE FUNCTIONS ---------------- */

    private static void addSchedule() {
        ScheduleBean s = new ScheduleBean();

        System.out.print("Enter Schedule ID: ");
        s.setScheduleID(sc.nextLine());

        System.out.print("Enter Flight ID: ");
        s.setFlightID(sc.nextLine());

        System.out.print("Enter Route ID: ");
        s.setRouteID(sc.nextLine());

        System.out.print("Enter Available Days (e.g. Mon, Wed, Fri): ");
        s.setAvailableDays(sc.nextLine());

        System.out.print("Enter Departure Time: ");
        s.setDepartureTime(sc.nextLine());
        
        System.out.print("Enter Travel Duration: ");
        s.setTravelDuration(sc.nextInt());

        String result = adminService.addSchedule(s);
        System.out.println("Add Schedule Result: " + result);
    }

    private static void viewSchedules() {
        ArrayList<ScheduleBean> schedules = adminService.viewByAllSchedule();
        if (schedules.isEmpty()) System.out.println("No schedules available!");
        else schedules.forEach(System.out::println);
    }

    private static void deleteSchedule() {
        System.out.print("Enter Schedule ID to delete: ");
        String id = sc.nextLine();
        ArrayList<String> ids = new ArrayList<>();
        ids.add(id);
        boolean deleted = adminService.removeSchedule(ids);
        System.out.println(deleted ? "Schedule deleted!" : "Schedule not found.");
    }

    private static void modifySchedule() {
        System.out.print("Enter Schedule ID to modify: ");
        String id = sc.nextLine();
        ScheduleBean existing = adminService.viewByScheduleId(id);

        if (existing == null) {
            System.out.println("Schedule not found!");
            return;
        }

        System.out.print("Enter new Departure Time (leave blank to keep current): ");
        String newDep = sc.nextLine();
        if (!newDep.isEmpty()) existing.setDepartureTime(newDep);

        System.out.print("Enter new Travel Duration (leave blank to keep current): ");
        String newDurationStr = sc.nextLine();

        if (!newDurationStr.isEmpty()) {
            int newDuration = Integer.parseInt(newDurationStr); // convert string to int
            existing.setTravelDuration(newDuration); // call your int setter
        }


        boolean updated = adminService.modifySchedule(existing);
        System.out.println(updated ? "Schedule updated!" : "Update failed.");
    }


    /* ---------------- CUSTOMER FUNCTIONS ---------------- */

    private static void viewScheduleByRoute() {
        System.out.print("Enter Source: ");
        String src = sc.nextLine();
        System.out.print("Enter Destination: ");
        String dest = sc.nextLine();
        System.out.print("Enter Date (DD-MM-YYYY or any string): ");
        String date = sc.nextLine();

        ArrayList<ScheduleBean> schedules = customerService.viewScheduleByRoute(src, dest, date);
        if (schedules.isEmpty()) System.out.println("No schedules found!");
        else schedules.forEach(System.out::println);
    }

    private static void reserveTicket() {
        ReservationBean rb = new ReservationBean();
        System.out.print("Enter User ID: ");
        rb.setUserID(sc.nextLine());
        System.out.print("Enter Schedule ID: ");
        rb.setScheduleID(sc.nextLine());
        System.out.print("Enter No. of Seats: ");
        rb.setNoOfSeats(sc.nextInt());
        rb.setTotalFare(rb.getNoOfSeats() * 6000.0); // simple fare calc
        sc.nextLine();

        ArrayList<PassengerBean> passengers = new ArrayList<>();
        for (int i = 0; i < rb.getNoOfSeats(); i++) {
            PassengerBean p = new PassengerBean();
            System.out.print("Enter Passenger " + (i + 1) + " Name: ");
            p.setName(sc.nextLine());
            System.out.print("Gender: ");
            p.setGender(sc.nextLine());
            System.out.print("Age: ");
            p.setAge(sc.nextInt());
            sc.nextLine();
            passengers.add(p);
        }

        String result = customerService.reserveTicket(rb, passengers);
        System.out.println("Reservation Result: " + result);
    }

    private static void cancelTicket() {
        System.out.print("Enter Reservation ID to cancel: ");
        String id = sc.nextLine();
        boolean cancelled = customerService.cancelTicket(id);
        System.out.println(cancelled ? "Ticket cancelled!" : "Reservation not found!");
    }

    private static void viewTicket() {
        System.out.print("Enter Reservation ID: ");
        String id = sc.nextLine();
        Map<ReservationBean, PassengerBean> ticket = customerService.viewTicket(id);
        if (ticket.isEmpty()) System.out.println("No ticket found!");
        else ticket.forEach((res, pass) -> {
            System.out.println(res);
            System.out.println(pass);
        });
    }
}
