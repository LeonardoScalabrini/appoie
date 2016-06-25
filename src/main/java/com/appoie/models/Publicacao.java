package com.appoie.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.exceptions.NumeroFotosPublicacaoInvalido;
import com.appoie.ids.CidadeId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import static com.appoie.utils.ValidationObject.*;

import static com.appoie.utils.ValidationString.*;

@Entity
public class Publicacao extends BasicEntity<PublicacaoId>{
	
	private UsuarioId usuarioId;
	private CidadeId cidadeId;
	private String titulo;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@Temporal(TemporalType.DATE)
	private Calendar dataPublicação = Calendar.getInstance();
	private List<FotoPublicacao> fotos = new ArrayList<>();
	
	private Publicacao() {
		super(new PublicacaoId());
	}
	
	public Publicacao(UsuarioId usuarioId, 
					  CidadeId cidadeId, 
					  String titulo, 
					  String descrição, 
					  Categoria categoria) throws NumeroFotosPublicacaoInvalido{
		this();
		isNull(usuarioId);
		isNull(cidadeId);
		this.usuarioId = usuarioId;
		this.cidadeId = cidadeId;
		setTitulo(titulo);
		setDescrição(descrição);
		setCategoria(categoria);
		
		if (fotos.size() < 1 || fotos.size() > 3) {

			throw new NumeroFotosPublicacaoInvalido();

		} else {
			for (FotoPublicacao fotoPublicacao : fotos) {
				this.fotos.add(fotoPublicacao);

			}
		}
	}
	
	public Publicacao(PublicacaoCommand command) {
		this();
		isNull(command);
		this.usuarioId = command.usuarioId;
		this.cidadeId = command.cidadeId;
		this.titulo = command.titulo;
		this.descricao = command.descricao;
		this.categoria = command.categoria;
		this.dataPublicação = command.dataPublicação;

	}

	public void setTitulo(String titulo) {
		isNullOrEmpty(titulo);
		this.titulo = titulo;
	}

	public void setDescrição(String descrição) {
		isNullOrEmpty(descrição);
		this.descricao = descrição;
	}
	
	public void setCategoria(Categoria categoria) {
		isNull(categoria);
		this.categoria = categoria;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescrição() {
		return descricao;
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
