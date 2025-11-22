package com.ust.frs.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ust.frs.bean.CredentialsBean;
import com.ust.frs.bean.PassengerBean;
import com.ust.frs.bean.ProfileBean;
import com.ust.frs.bean.ReservationBean;
import com.ust.frs.bean.ScheduleBean;

public interface Customer {
	String registerUser(CredentialsBean cb);
	String registerUserProfile(ProfileBean p);
	ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, String date) throws ParseException;
	boolean cancelTicket(int id);
	String reserveTicket(ReservationBean reservationBean, ArrayList<PassengerBean> passengers); 
	Map<ReservationBean, List<PassengerBean>> viewTicket(int id);
	Map<ReservationBean,PassengerBean> printTicket(String reservationId);
	
}
