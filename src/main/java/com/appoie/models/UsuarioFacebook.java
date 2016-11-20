package com.appoie.models;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.ids.UsuarioId;

@Entity
public class UsuarioFacebook extends BasicEntity<UsuarioId> {	   
	
    private String idFacebook;
    
    @Temporal(TemporalType.DATE)
	@AttributeOverride(name="calendar",column=@Column(name="data_acesso_facebook"))
    private Calendar calendar = Calendar.getInstance();
    
	private String nomeCidade;
	
	public UsuarioFacebook() {
	
	}
	
	public UsuarioFacebook(SalvarUsuarioFacebookCommand command, UsuarioId usuarioId) throws Exception {
		this();
		setId(usuarioId);
		setIdFacebook(command.idFacebook);
		setNomeCidade(command.nomeCidade);
	}

	private void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
		
	}

	private void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
		
	}

	public String getIdFacebook() {
		return idFacebook;
	}
	
	public String getNomeCidade(){
		return nomeCidade;
	}
}
