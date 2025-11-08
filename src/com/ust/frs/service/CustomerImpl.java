package com.ust.frs.service;

import java.util.*;
import com.ust.frs.bean.*;
import com.ust.frs.dao.*;

public class CustomerImpl implements Customer {

    @Override
    public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, String date) {
        ArrayList<ScheduleBean> result = new ArrayList<>();
        for (ScheduleBean s : DataStore.schedules) {
            for (RouteBean r : DataStore.routes) {
                if (r.getRouteID().equals(s.getRouteID())
                        && r.getSource().equalsIgnoreCase(source)
                        && r.getDestination().equalsIgnoreCase(destination)) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    @Override
    public String reserveTicket(ReservationBean reservationBean, ArrayList<PassengerBean> passengers) {
        reservationBean.setReservationID("RSV" + (DataStore.reservations.size() + 1));
        DataStore.reservations.add(reservationBean);
        System.out.println("Passengers booked: " + passengers.size());
        return "SUCCESS";
    }

    @Override
    public boolean cancelTicket(String reservationId) {
        return DataStore.reservations.removeIf(r -> r.getReservationID().equalsIgnoreCase(reservationId));
    }

    @Override
    public Map<ReservationBean, PassengerBean> viewTicket(String reservationId) {
        Map<ReservationBean, PassengerBean> map = new HashMap<>();
        for (ReservationBean r : DataStore.reservations) {
            if (r.getReservationID().equalsIgnoreCase(reservationId)) {
                PassengerBean p = new PassengerBean();
                p.setName("Passenger_1");
                map.put(r, p);
                break;
            }
        }
        return map;
    }

    @Override
    public Map<ReservationBean, PassengerBean> printTicket(String reservationId) {
        // For now, same as viewTicket (later can be extended for PDF export)
        return viewTicket(reservationId);
    }
}
