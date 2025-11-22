package com.ust.frs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Scanner;

import com.ust.frs.bean.CredentialsBean;
import com.ust.frs.bean.PassengerBean;
import com.ust.frs.bean.ReservationBean;
import com.ust.frs.bean.ScheduleBean;
import com.ust.frs.service.Customer;

public class CustomerDAO implements Customer {
	public static Connection con = getCon();
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airon","root","pass@word1");	
		}
		catch(ClassNotFoundException cnf) {
			System.out.println(cnf);
		}
		catch(SQLException sql) {
			System.out.println(sql);
		}
		return con;
	}
	
	@Override
	public ArrayList<ScheduleBean> viewScheduleByRoute(String Source, String Destination, String Date) throws ParseException{
		ArrayList<ScheduleBean> all = new ArrayList<ScheduleBean>();
		
        String userInput = Date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsedDate = dateFormat.parse(userInput);
        
        Date sqlDate = new Date(parsedDate.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sqlDate);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String dayOfWeekStr = "";
        
        dayOfWeekStr= switch(dayOfWeek) {
	        case Calendar.SUNDAY	-> "Sun";
	        case Calendar.MONDAY	-> "Mon";
	        case Calendar.TUESDAY	-> "Tue";
	        case Calendar.WEDNESDAY	-> "Wed";
	        case Calendar.THURSDAY	->  "Thu";
	        case Calendar.FRIDAY	->  "Fri";
	        case Calendar.SATURDAY	->  "Sat";
	        default	->  "";
        };
		try {
			ps = con.prepareStatement("SELECT s.ScheduleId, s.FlightId, s.RouteId, s.TravelDuration, s.AvailableDays, s.DepartureTime "
			        + "FROM frs_tbl_schedule s "
			        + "JOIN frs_tbl_route r ON s.RouteId = r.RouteId "
			        + "JOIN frs_tbl_flight f ON s.FlightId = f.FlightId "
			        + "WHERE r.Source = ? "
			        + "AND r.Destination = ? "
			        + "AND s.AvailableDays LIKE ?;");
			ps.setString(1, Source);
			ps.setString(2, Destination);
			ps.setString(3, "%" + dayOfWeekStr + "%");
			rs=ps.executeQuery();
			if (rs.next()) {
				ScheduleBean sb=new ScheduleBean();
				sb.setScheduleID(rs.getInt(1));
				sb.setFlightID(rs.getInt(2));
				sb.setRouteID(rs.getInt(3));
				sb.setTravelDuration(rs.getInt(4));
				sb.setAvailableDays(rs.getString(5));
				sb.setDepartureTime(rs.getString(6));
				all.add(sb);
				
			}
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}

		return all;
	}

	@Override
	public boolean cancelTicket(int reservationId) {
		int i=0;
		try {
			ps = con.prepareStatement("delete from frs_tbl_passenger where ReservationId =?");
			ps.setInt(1, reservationId);
			i=ps.executeUpdate();
			
			ps = con.prepareStatement("delete from frs_tbl_reservation where ReservationId =?");
			ps.setInt(1, reservationId);
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? true : false;
	}

	@Override
	public String reserveTicket(ReservationBean rb, ArrayList<PassengerBean> passengers) {
	    int i = 0;
	    int reservationId = -1; // Variable to store the generated reservation ID
	    try {
	        // Insert reservation
	        ps = con.prepareStatement("insert into frs_tbl_reservation (UserId, ScheduleId, ReservationType, BookingDate, JourneyDate, NoOfSeats, TotalFare, BookingStatus) values(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, rb.getUserID());
	        ps.setInt(2, rb.getScheduleID());
	        ps.setString(3, rb.getReservationType());
	        ps.setString(4, rb.getBookingDate());
	        ps.setString(5, rb.getJourneyDate());
	        ps.setInt(6, rb.getNoOfSeats());
	        ps.setDouble(7, rb.getTotalFare());
	        ps.setInt(8, 1);
	        i = ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            reservationId = rs.getInt(1); 
	        }

	        if (reservationId != -1) {
	            for (PassengerBean pb : passengers) {
	                ps = con.prepareStatement("insert into frs_tbl_passenger (ReservationId, SeatNo, Name, Gender, Age) values(?,?,?,?,?)");
	                ps.setInt(1, reservationId); // Use the reservationId from the previous step
	                ps.setInt(2, pb.getSeatNo());
	                ps.setString(3, pb.getName());
	                ps.setString(4, pb.getGender());
	                ps.setInt(5, pb.getAge());
	                i = ps.executeUpdate();
	            }
	        }
	    } catch (SQLException sql) {
	        System.out.println("SQL Error: " + sql);
	    }

	    // Return success if at least one row was affected
	    return (i > 0) ? "SUCCESS" : "FAIL";
	}


	@Override
	public Map<ReservationBean, List<PassengerBean>> viewTicket(int reservationId) {
		Map<ReservationBean, List<PassengerBean>> ticketDetails = new HashMap<>();
	    
	    ReservationBean rb = null;
	    List<PassengerBean> passengerList = new ArrayList<>();

	    try {
	        // Retrieve reservation details
	        ps = con.prepareStatement("SELECT * FROM frs_tbl_reservation WHERE ReservationId = ?");
	        ps.setInt(1, reservationId);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            rb = new ReservationBean();
	            rb.setReservationID(rs.getInt("ReservationId"));
	            rb.setUserID(rs.getString("UserId"));
	            rb.setScheduleID(rs.getInt("ScheduleId"));
	            rb.setReservationType(rs.getString("ReservationType"));
	            rb.setBookingDate(rs.getString("BookingDate"));
	            rb.setJourneyDate(rs.getString("JourneyDate"));
	            rb.setNoOfSeats(rs.getInt("NoOfSeats"));
	            rb.setTotalFare(rs.getDouble("TotalFare"));
	            rb.setBookingStatus(rs.getInt("BookingStatus"));
	        }

	        // Retrieve passenger details
	        ps = con.prepareStatement("SELECT * FROM frs_tbl_passenger WHERE ReservationId = ?");
	        ps.setInt(1, reservationId);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            PassengerBean pb = new PassengerBean();
	            pb.setPassengerID(rs.getInt("PassengerId"));
	            pb.setReservationID(rs.getInt("ReservationId"));
	            pb.setSeatNo(rs.getInt("SeatNo"));
	            pb.setName(rs.getString("Name"));
	            pb.setGender(rs.getString("Gender"));
	            pb.setAge(rs.getInt("Age"));
	            
	            passengerList.add(pb); // Add the passenger to the list
	        }

	        // Add the reservation and its passengers to the map
	        ticketDetails.put(rb, passengerList);

	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle SQL exceptions
	    }

	    return ticketDetails;
	}

	@Override
	public Map<ReservationBean, PassengerBean> printTicket(String reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registerUser(CredentialsBean cb) {
		int i=0;
		try {
			ps = con.prepareStatement("insert into frs_tbl_user_credentials (UserId,Password,UserType,LoginStatus) values(?,?,?,?)");
			ps.setString(1, cb.getUserID());
			ps.setString(2, cb.getPassword());
			ps.setString(3, "C");
			ps.setInt(4, 0);
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? "SUCCESS" : "FAIL";
	}

	@Override
	public String registerUserProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
