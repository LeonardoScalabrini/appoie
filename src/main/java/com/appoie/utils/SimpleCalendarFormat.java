package com.appoie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleCalendarFormat {

	private static final String SIMPLE_FORMAT = "yyyy-MM-dd";
	private static String format = SIMPLE_FORMAT;

	public static Calendar parse(String stringData) {

		SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		Date data;
		try {
			data = sdf.parse(stringData);
			calendar.setTime(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		format = SIMPLE_FORMAT;
		return calendar;
	}

	public static void format(String format) {
		SimpleCalendarFormat.format = format;
	}
	
	public static String getFormat(){
		return format;
	}
}
