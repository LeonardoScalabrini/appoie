package com.appoie.commands;


import com.appoie.models.Sexo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

	public class SalvarUsuarioFacebookCommand {
        public final Long idFacebook;
		public final String nome;
		public final String sobrenome;		
		public final Sexo sexo;
		public final String email;		
		public final String nomeCidade;	

		@JsonCreator
		public SalvarUsuarioFacebookCommand(@JsonProperty(value ="idFacebook")Long idFacebook,
				@JsonProperty(value = "nome") String nome,				
				@JsonProperty(value = "sobrenome") String sobrenome,
				@JsonProperty(value = "sexo") String sexo,	
				@JsonProperty(value ="email") String email,
				@JsonProperty(value = "nomeCidade") String nomeCidade) {
            this.idFacebook =idFacebook;
			this.nome = nome;
			this.sobrenome = sobrenome;			
			this.sexo = Sexo.valueOf(sexo.toUpperCase());			
			this.email = email;	
			this.nomeCidade =nomeCidade;			
			
		}

	}

