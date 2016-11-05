package com.appoie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleCalendarFormat {

	private static final String SIMPLE_FORMAT = "yyyy-MM-dd";

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

		return calendar;
	}

	// public static Calendar parse(String stringData) {
	// Calendar c = null;
	// try {
	//
	// SimpleDateFormat formatoData = new SimpleDateFormat(SIMPLE_FORMAT);
	//
	// c = Calendar.getInstance();
	//
	// c.setTime(formatoData.parse(stringData));
	//
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return c;
	// }

}
