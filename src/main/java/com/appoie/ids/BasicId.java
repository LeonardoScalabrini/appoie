package com.appoie.ids;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import com.appoie.utils.ValidationString;

@MappedSuperclass
@Embeddable
public abstract class BasicId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public BasicId(String id) {
		setId(id);
	}

	public BasicId() {		
		this.id = UUID.randomUUID().toString();
	}

	public final String getValue() {
		return this.id;
	}
	
	private final void setId(String id){
		ValidationString.isNullOrEmpty(id);
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BasicId)) {
			return false;
		}
		BasicId other = (BasicId) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
}
