package com.appoie.commands;


import com.appoie.models.Sexo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

	public class SalvarUsuarioFacebookCommand {
        public final String idFacebook;
		public final String nome;
		public final String sobrenome;	
		public final String dataDeNascimento;
		public final Sexo sexo;
		public final String email;		
		public final String nomeCidade;
		public final String foto;
		public final String nomeEstado;

		@JsonCreator
		public SalvarUsuarioFacebookCommand(@JsonProperty(value ="idFacebook")String idFacebook,
				@JsonProperty(value = "nome") String nome,				
				@JsonProperty(value = "sobrenome") String sobrenome,
				@JsonProperty(value = "dataDeNascimento") String dataDeNascimento,
				@JsonProperty(value = "sexo") String sexo,	
				@JsonProperty(value ="email") String email,
				@JsonProperty(value = "nomeCidade") String nomeCidade,
				@JsonProperty("foto")String foto, 
				@JsonProperty("nomeEstado") String estado) {
            this.idFacebook =idFacebook;
			this.nome = nome;
			this.sobrenome = sobrenome;	
			this.dataDeNascimento =dataDeNascimento;
			this.sexo = Sexo.valueOf(sexo.toUpperCase());			
			this.email = email;	
			this.nomeCidade =nomeCidade;
			this.foto =foto;
			this.nomeEstado = estado;
			
		}

		public String getFoto() {
			return foto;
		}

		public String getIdFacebook() {
			return idFacebook;
		}

		public String getNome() {
			return nome;
		}

		public String getSobrenome() {
			return sobrenome;
		}

		public String getDataDeNascimento() {
			return dataDeNascimento;
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

