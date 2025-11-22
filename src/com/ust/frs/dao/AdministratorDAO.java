package com.ust.frs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ust.frs.bean.*;
import com.ust.frs.service.Administrator;

public class AdministratorDAO implements Administrator {
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

	public String addFlight(FlightBean fb) {
		int i=0;
		try {
			ps = con.prepareStatement("insert into frs_tbl_flight (FlightName,SeatingCapacity,ReservationCapacity) values(?,?,?)");
			ps.setString(1, fb.getFlightName());
			ps.setInt(2, fb.getSeatingCapacity());
			ps.setInt(3, fb.getReservationCapacity());
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? "SUCCESS" : "FAIL";

	}

	public boolean modifyFlight(FlightBean fb) {
		int i = 0;
		try {

			StringBuilder query = new StringBuilder("UPDATE frs_tbl_flight SET ");

			boolean first = true;

			if (fb.getFlightName() != null && !fb.getFlightName().isEmpty()) {
				if (!first) query.append(", ");
				query.append("FlightName = ?");
				first = false;
			}

			if (fb.getSeatingCapacity() > 0) {
				if (!first) query.append(", ");
				query.append("SeatingCapacity = ?");
				first = false;
			}

			if (fb.getReservationCapacity() > 0) {
				if (!first) query.append(", ");
				query.append("ReservationCapacity = ?");
				first = false;
			}

			query.append(" WHERE FlightId = ?");

			ps = con.prepareStatement(query.toString());

			int index = 1;

			if (fb.getFlightName() != null && !fb.getFlightName().isEmpty()) {
				ps.setString(index++, fb.getFlightName());
			}

			if (fb.getSeatingCapacity() > 0) {
				ps.setInt(index++, fb.getSeatingCapacity());
			}

			if (fb.getReservationCapacity() > 0) {
				ps.setInt(index++, fb.getReservationCapacity());
			}

			ps.setInt(index, fb.getFlightID());

			i = ps.executeUpdate();
		} catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? true : false;
	}



	public boolean removeFlight(int flightId) {
		int i=0;
		try {
			ps = con.prepareStatement("delete from frs_tbl_flight where FlightId =?");
			ps.setInt(1, flightId);
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? true : false;
	}

	public FlightBean viewByFlightId(int flightId) {
		FlightBean fb = new FlightBean();
		try {
			ps = con.prepareStatement("select * from frs_tbl_flight where FlightId =?");
			ps.setInt(1, flightId);
			rs=ps.executeQuery();
			if (rs.next()) {
				fb.setFlightID(rs.getInt(1));
				fb.setFlightName(rs.getString(2));
				fb.setSeatingCapacity(rs.getInt(3));
				fb.setReservationCapacity(rs.getInt(4));
			}
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return fb;
	}

	public ArrayList<FlightBean> viewByAllFlights() {
		ArrayList<FlightBean> all=new ArrayList<FlightBean>();
		try	{
			ps=con.prepareStatement("select * from frs_tbl_flight");
			rs=ps.executeQuery();
			while(rs.next()) {
				FlightBean fb=new FlightBean();
				fb.setFlightID(rs.getInt(1));
				fb.setFlightName(rs.getString(2));
				fb.setSeatingCapacity(rs.getInt(3));
				fb.setReservationCapacity(rs.getInt(4));
				all.add(fb);
			}
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		return all;
	}

	@Override
	public String addSchedule(ScheduleBean sb) {
		int i=0;
		try {
			ps = con.prepareStatement("insert into frs_tbl_schedule ( FlightId, RouteId, TravelDuration, AvailableDays, DepartureTime) values(?,?,?,?,?)");
			ps.setInt(1, sb.getFlightID());
			ps.setInt(2, sb.getRouteID());
			ps.setInt(3, sb.getTravelDuration());
			ps.setString(4, sb.getAvailableDays());
			ps.setString(5, sb.getDepartureTime());
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? "SUCCESS" : "FAIL";
	}

	@Override
	public boolean modifySchedule(ScheduleBean sb) {
		int i = 0;
		try {
			StringBuilder query = new StringBuilder("UPDATE frs_tbl_schedule SET ");

			boolean first = true;

			if (sb.getTravelDuration() > 0) {
				if (!first) query.append(", ");
				query.append("TravelDuration = ?");
				first = false;
			}

			if (sb.getAvailableDays() != null && !sb.getAvailableDays().isEmpty()) {
				if (!first) query.append(", ");
				query.append("AvailableDays = ?");
				first = false;
			}

			if (sb.getDepartureTime() != null && !sb.getDepartureTime().isEmpty()) {
				if (!first) query.append(", ");
				query.append("DepartureTime = ?");
				first = false;
			}

			query.append(" WHERE ScheduleId = ?");

			ps = con.prepareStatement(query.toString());

			int index = 1;

			if (sb.getTravelDuration() > 0) {
				ps.setInt(index++, sb.getTravelDuration());
			}

			if (sb.getAvailableDays() != null && !sb.getAvailableDays().isEmpty()) {
				ps.setString(index++, sb.getAvailableDays());
			}

			if (sb.getDepartureTime() != null && !sb.getDepartureTime().isEmpty()) {
				ps.setString(index++, sb.getDepartureTime());
			}

			ps.setInt(index, sb.getScheduleID());

			i = ps.executeUpdate();
		} catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i == 1) ? true : false;
	}


	@Override
	public boolean removeSchedule(int scheduleId) {
		int i=0;
		try {
			ps = con.prepareStatement("delete from frs_tbl_schedule where ScheduleId =?");
			ps.setInt(1, scheduleId);
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? true : false;
	}

	@Override
	public String addRoute(RouteBean rb) {
		int i=0;
		try {
			ps = con.prepareStatement("insert into frs_tbl_route ( Source, Destination, Distance, Fare) values(?,?,?,?)");
			ps.setString(1, rb.getSource());
			ps.setString(2, rb.getDestination());
			ps.setInt(3, rb.getDistance());
			ps.setDouble(4, rb.getFare());
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? "SUCCESS" : "FAIL";

	}

	@Override
	public boolean modifyRoute(RouteBean rb) {
		int i = 0;
		try {

			StringBuilder query = new StringBuilder("UPDATE frs_tbl_route SET ");

			boolean first = true;

			if (rb.getSource() != null && !rb.getSource().isEmpty()) {
				if (!first) query.append(", ");
				query.append("Source = ?");
				first = false;
			}

			if (rb.getDestination() != null && !rb.getDestination().isEmpty()) {
				if (!first) query.append(", ");
				query.append("Destination = ?");
				first = false;
			}

			if (rb.getDistance() > 0) {
				if (!first) query.append(", ");
				query.append("Distance = ?");
				first = false;
			}

			if (rb.getFare() > 0) {
				if (!first) query.append(", ");
				query.append("Fare = ?");
				first = false;
			}

			query.append(" WHERE RouteId = ?");

			ps = con.prepareStatement(query.toString());

			int index = 1;

			if (rb.getSource() != null && !rb.getSource().isEmpty()) {
				ps.setString(index++, rb.getSource());
			}
			if (rb.getDestination() != null && !rb.getDestination().isEmpty()) {
				ps.setString(index++, rb.getDestination());
			}
			if (rb.getDistance() > 0) {
				ps.setInt(index++, rb.getDistance());
			}
			if (rb.getFare() > 0) {
				ps.setDouble(index++, rb.getFare());
			}

			ps.setInt(index, rb.getRouteID()); // WHERE condition
			i = ps.executeUpdate();

		} catch (SQLException sql) {
			System.out.println("Error updating route: " + sql);
		}
		return (i==1)? true : false;
	}

	@Override
	public boolean removeRoute(int routeId) {
		int i=0;
		try {
			ps = con.prepareStatement("delete from frs_tbl_route where RouteId =?");
			ps.setInt(1, routeId);
			i=ps.executeUpdate();
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return (i==1)? true : false;
	}

	@Override
	public RouteBean viewByRouteId(int routeId) {
		RouteBean rb = new RouteBean();
		try {
			ps = con.prepareStatement("select * from frs_tbl_route where RouteId =?");
			ps.setInt(1, routeId);
			rs=ps.executeQuery();
			if (rs.next()) {
				rb.setRouteID(rs.getInt(1));
				rb.setSource(rs.getString(2));
				rb.setDestination(rs.getString(3));
				rb.setDistance(rs.getInt(4));
				rb.setFare(rs.getDouble(5));				
			}
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return rb;
	}


	@Override
	public ArrayList<RouteBean> viewByAllRoute(){
		ArrayList<RouteBean> all=new ArrayList<RouteBean>();
		try	{
			ps=con.prepareStatement("select * from frs_tbl_route");
			rs=ps.executeQuery();
			while(rs.next()) {
				RouteBean rb=new RouteBean();
				rb.setRouteID(rs.getInt(1));
				rb.setSource(rs.getString(2));
				rb.setDestination(rs.getString(3));
				rb.setDistance(rs.getInt(4));
				rb.setFare(rs.getDouble(5));
				all.add(rb);
			}
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		return all;
	}

	@Override
	public ArrayList<ScheduleBean> viewByAllSchedule() {
		ArrayList<ScheduleBean> all=new ArrayList<ScheduleBean>();
		try	{
			ps=con.prepareStatement("select * from frs_tbl_schedule");
			rs=ps.executeQuery();
			while(rs.next()) {
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
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		return all;
	}

	@Override
	public ScheduleBean viewByScheduleId(int scheduleId) {
		ScheduleBean sb = new ScheduleBean();
		try {
			ps = con.prepareStatement("select * from frs_tbl_schedule where ScheduleId =?");
			ps.setInt(1, scheduleId);
			rs=ps.executeQuery();
			if (rs.next()) {
				sb.setScheduleID(rs.getInt(1));
				sb.setFlightID(rs.getInt(2));
				sb.setRouteID(rs.getInt(3));
				sb.setTravelDuration(rs.getInt(4));
				sb.setAvailableDays(rs.getString(5));
				sb.setDepartureTime(rs.getString(6));
			}
		}
		catch (SQLException sql) {
			System.out.println(sql);
		}
		return sb;
	}

	@Override
	public ArrayList<PassengerBean> viewPassengersByFlight() {
		ArrayList<PassengerBean> all=new ArrayList<PassengerBean>();
		try	{
			ps=con.prepareStatement("select * from frs_tbl_passenger");
			rs=ps.executeQuery();
			while(rs.next()) {
				PassengerBean pb=new PassengerBean();
				pb.setPassengerID(rs.getInt(1));
				pb.setReservationID(rs.getInt(2));
				pb.setSeatNo(rs.getInt(3));
				pb.setName(rs.getString(4));
				pb.setGender(rs.getString(5));
				pb.setAge(rs.getInt(6));
				all.add(pb);
			}
		}
		catch(SQLException sql)
		{
			System.out.println(sql);
		}
		return all;
	}

}
