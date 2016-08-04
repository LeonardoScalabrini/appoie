package com.appoie.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static com.appoie.utils.ValidationString.isNullOrEmpty;

import javax.persistence.Embeddable;

import com.appoie.exceptions.EmailFormatoException;

import static com.appoie.utils.ValidationString.*;

@Embeddable
public class Email {

	private String value;
	
	private Email() {

	}
	
	public Email(String value) throws EmailFormatoException{		
		this();
		setValue(value);
	}	
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value) throws EmailFormatoException{
		isNullOrEmpty(value);
		validarFormato(value);
		this.value = value;
	}
	
	private void validarFormato(String value) throws EmailFormatoException{
		Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.find()) {
			throw new EmailFormatoException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
