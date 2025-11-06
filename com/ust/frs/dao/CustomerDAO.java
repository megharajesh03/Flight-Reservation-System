package com.ust.frs.dao;

import java.util.ArrayList;
import java.util.Map;

import com.ust.frs.bean.PassengerBean;
import com.ust.frs.bean.ReservationBean;
import com.ust.frs.bean.ScheduleBean;
import com.ust.frs.service.Customer;

public class CustomerDAO implements Customer {

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
