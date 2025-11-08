package com.ust.frs.service;

import java.util.*;
import com.ust.frs.bean.*;
import com.ust.frs.dao.*;

public class AdministratorImpl implements Administrator {

    private FlightDAO flightDAO = new FlightDAO();
    private RouteDAO routeDAO = new RouteDAO();


    @Override
    public String addFlight(FlightBean flightBean) {
        if (flightBean == null || flightBean.getFlightName() == null || flightBean.getFlightName().isEmpty()) {
            return "FAIL";
        }
        return flightDAO.createFlight(flightBean);
    }

    @Override
    public boolean modifyFlight(ArrayList<FlightBean> flightBeanArray) {
        boolean result = false;
        for (FlightBean f : flightBeanArray) {
            result = flightDAO.updateFlight(f);
        }
        return result;
    }

    @Override
    public boolean removeFlight(ArrayList<FlightBean> flightIDArray) {
        for (FlightBean f : flightIDArray) {
            if (flightDAO.deleteFlight(f.getFlightID())) return true;
        }
        return false;        
    }

    @Override
    public String addSchedule(ScheduleBean scheduleBean) {
        if (scheduleBean == null) return "FAIL";
        DataStore.schedules.add(scheduleBean);
        return "SUCCESS";
    }

    @Override
    public boolean modifySchedule(ScheduleBean scheduleBean) {
        for (int i = 0; i < DataStore.schedules.size(); i++) {
            if (DataStore.schedules.get(i).getScheduleID().equalsIgnoreCase(scheduleBean.getScheduleID())) {
                DataStore.schedules.set(i, scheduleBean);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeSchedule(ArrayList<String> scheduleId) {
        for (String id : scheduleId) {
            if (DataStore.schedules.removeIf(s -> s.getScheduleID().equalsIgnoreCase(id))) return true;
        }
        return false;
    }

    @Override
    public String addRoute(RouteBean routeBean) {
        if (routeBean == null || routeBean.getSource() == null || routeBean.getDestination() == null) {
            return "FAIL";
        }
        return routeDAO.createRoute(routeBean);
    }

    @Override
    public boolean modifyRoute(RouteBean routeBean) {
        for (int i = 0; i < DataStore.routes.size(); i++) {
            if (DataStore.routes.get(i).getRouteID().equalsIgnoreCase(routeBean.getRouteID())) {
                DataStore.routes.set(i, routeBean);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeRoute(ArrayList<String> routeId) {
        for (String id : routeId) {
            if (routeDAO.deleteRoute(id)) return true;
        }
        return false;
    }

    @Override
    public FlightBean viewByFlightId(String flightId) {
        return flightDAO.findById(flightId);
    }

    @Override
    public RouteBean viewByRouteId(String routeId) {
        return routeDAO.findById(routeId);
    }

    @Override
    public ArrayList<FlightBean> viewByAllFlights() {
        return new ArrayList<>(flightDAO.findAll());
    }

    @Override
    public ArrayList<RouteBean> viewByAllRoute() {
        return new ArrayList<>(routeDAO.findAll());
    }

    @Override
    public ArrayList<ScheduleBean> viewByAllSchedule() {
        return new ArrayList<>(DataStore.schedules);
    }

    @Override
    public ScheduleBean viewByScheduleId(String scheduleId) {
        for (ScheduleBean s : DataStore.schedules) {
            if (s.getScheduleID().equalsIgnoreCase(scheduleId)) return s;
        }
        return null;
    }

    @Override
    public ArrayList<PassengerBean> viewPassengersByFlight(String scheduleId) {
        ArrayList<PassengerBean> result = new ArrayList<>();
        for (ReservationBean r : DataStore.reservations) {
            if (r.getScheduleID().equalsIgnoreCase(scheduleId)) {
                PassengerBean p = new PassengerBean();
                p.setName("Demo Passenger for " + r.getReservationID());
                result.add(p);
            }
        }
        return result;
    }

}
