package com.appoie.utils;

public class ValidationString {
	
	public static void isNull(String string){
		if (string == null){
			throw new IllegalArgumentException("A String não pode ser nula");
		}
	}
	
	public static void isNullOrEmpty(String string){
		isNull(string);
		if(string.isEmpty()){
			throw new IllegalArgumentException("A String não pode ser vazia");
		}
	}
	
	public static void isNull(String strings[]){
		for (String string : strings) {
			isNull(string);
		}
		
	}
	
	public static void isNullOrEmpty(String strings[]){
		for (String string : strings) {
			isNullOrEmpty(string);
		}
	}
}
