package com.ust.frs.service;

import java.util.*;
import com.ust.frs.bean.*;
import com.ust.frs.dao.*;

public class CustomerImpl implements Customer {

	@Override
	public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelTicket(String reservationId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String reserveTicket(ReservationBean reservationBean, ArrayList<PassengerBean> passengers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<ReservationBean, PassengerBean> viewTicket(String reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<ReservationBean, PassengerBean> printTicket(String reservationId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
