package com.appoie.utils;

public class Value {

	public static String StringOf(Object object) {
		if (object == null)
			return "";
		return object.toString();
	}

}
