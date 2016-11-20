package com.appoie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleCalendarFormat {

	private final String SIMPLE_FORMAT = "yyyy-MM-dd";
	private String format = SIMPLE_FORMAT;

	public Calendar parse(String stringData) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
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

	public SimpleCalendarFormat format(String format) {
		this.format = format;
		return this;
	}
	
	public String getFormat(){
		return format;
	}
}
