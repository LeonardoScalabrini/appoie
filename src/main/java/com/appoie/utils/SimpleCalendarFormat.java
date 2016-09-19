package com.appoie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleCalendarFormat {
	
	private static final String SIMPLE_FORMAT = "yyyy-mm-dd";  
	
	public static Calendar parse(String stringData){
		
		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		Date data;
		try {
			data = sdf.parse(stringData);
			calendar.setTime(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return calendar;
	}
}
