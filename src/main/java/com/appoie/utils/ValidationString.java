package com.appoie.utils;

public class ValidationString {
	
	public static Boolean isNull(String string){
		return string == null;
	}
	
	public static Boolean isNullOrEmpty(String string){
		return isNull(string) || string.isEmpty();
	}
}
