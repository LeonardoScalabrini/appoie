package com.appoie.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.ids.UsuarioFacebookId;
@Entity
public class UsuarioFacebook extends BasicEntity<UsuarioFacebookId> {
	 public final Long idFacebook;
		public String nome;
		public String sobrenome;
		@Enumerated(EnumType.STRING)
		public Sexo sexo;
		public String email;		
		public String nomeCidade;
		
		public UsuarioFacebook(SalvarUsuarioFacebookCommand command) {
			super(new UsuarioFacebookId());
			this.idFacebook = command.idFacebook;
			this.nome = command.nome;
			this.sobrenome = command.sobrenome;
			this.sexo = command.sexo;
			this.email = command.email;
			this.nomeCidade = command.nomeCidade;
		}

		public Long getIdFacebook() {
			return idFacebook;
		}

		public String getNome() {
			return nome;
		}

		public String getSobrenome() {
			return sobrenome;
		}

		public Sexo getSexo() {
			return sexo;
		}

		public String getEmail() {
			return email;
		}

		public String getNomeCidade() {
			return nomeCidade;
		}	
		
		
		
		
		
		
		
	    

}
