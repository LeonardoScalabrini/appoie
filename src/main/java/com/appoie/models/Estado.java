package com.appoie.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.appoie.ids.EstadoId;
import static com.appoie.utils.ValidationObject.*;

@Entity
public class Estado extends BasicEntity<EstadoId>{
	
	@OneToMany
	@JoinColumn(name="estado_id", referencedColumnName="id")
	private List<Cidade> cidades = new ArrayList<>(); 
	
	private Estado() {
		super(new EstadoId());
	}
	
	public Estado(Cidade cidade){
		this();
		isNull(cidade);
		this.cidades.add(cidade);
	}

}
