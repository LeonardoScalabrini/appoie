package com.appoie.models;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.ids.CidadeId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import static com.appoie.utils.ValidationObject.*;

import static com.appoie.utils.ValidationString.*;

@Entity
public class Publicacao extends BasicEntity<PublicacaoId>{
	
	@AttributeOverride(name="id",column=@Column(name="usuario_id"))
	private UsuarioId usuarioId;
	@AttributeOverride(name="id",column=@Column(name="cidade_id"))
	private CidadeId cidadeId;
	private String titulo;
	private String descrição;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@Temporal(TemporalType.DATE)
	private Calendar dataPublicação = Calendar.getInstance();
	
	private Publicacao() {
		super(new PublicacaoId());
	}
	
	public Publicacao(UsuarioId usuarioId, 
					  CidadeId cidadeId, 
					  String titulo, 
					  String descrição, 
					  Categoria categoria){
		this();
		isNull(usuarioId);
		isNull(cidadeId);
		this.usuarioId = usuarioId;
		this.cidadeId = cidadeId;
		setTitulo(titulo);
		setDescrição(descrição);
		setCategoria(categoria);
	}

	public void setTitulo(String titulo) {
		isNullOrEmpty(titulo);
		this.titulo = titulo;
	}

	public void setDescrição(String descrição) {
		isNullOrEmpty(descrição);
		this.descrição = descrição;
	}
	
	public void setCategoria(Categoria categoria) {
		isNull(categoria);
		this.categoria = categoria;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescrição() {
		return descrição;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public UsuarioId getUsuarioId() {
		return usuarioId;
	}
	
	public Calendar getDataPublicação(){
		return dataPublicação;
	}

	public CidadeId getCidadeId() {
		return cidadeId;
	}
	

}
