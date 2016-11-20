package com.appoie.commands;

import java.util.Calendar;

import com.appoie.utils.SimpleCalendarFormat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

	public class SalvarUsuarioFacebookCommand {
        public final String idFacebook;
		public final String nome;
		public final String sobrenome;	
		public final Calendar dataDeNascimento;
		public final String sexo;
		public final String email;		
		public final String nomeCidade;
		public final String foto;

		@JsonCreator
		public SalvarUsuarioFacebookCommand(@JsonProperty(value ="idFacebook")String idFacebook,
				@JsonProperty(value = "nome") String nome,				
				@JsonProperty(value = "sobrenome") String sobrenome,
				@JsonProperty(value = "dataDeNascimento") String dataDeNascimento,
				@JsonProperty(value = "sexo") String sexo,	
				@JsonProperty(value ="email") String email,
				@JsonProperty(value = "nomeCidade") String nomeCidade,
				@JsonProperty("foto")String foto) {
            this.idFacebook =idFacebook;
			this.nome = nome;
			this.sobrenome = sobrenome;
			System.out.println(dataDeNascimento);
			this.dataDeNascimento = new SimpleCalendarFormat().format("dd/MM/yyyy").parse(dataDeNascimento);
			this.sexo = sexo;			
			this.email = email;	
			this.nomeCidade =nomeCidade;
			this.foto =foto;	
		}
	}

