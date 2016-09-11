package com.appoie.utils;

public class ValidationObject {
	public static void isNull(Object o){
		if (o == null){
			throw new IllegalArgumentException("O objeto não pode ser nulo");
		}
	}
}
