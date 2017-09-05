package com.appoie.builders;

import java.util.Arrays;

import com.appoie.commands.SalvarPublicacaoCommand;
import com.appoie.ids.CidadeId;
import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Categoria;
import com.appoie.models.CriticidadeProblema;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;

public class PublicacaoBuilder {
	
	private FotoBuilder fotoBuilder = new FotoBuilder();
	private String titulo = "titulo";
	private String descricao = "descrição";
	private Categoria categoria = Categoria.ARBORIZACAO;
	private String foto = fotoBuilder.base64;
	private String estado = "Paraná";
	private String cidade = "Maringá";
	private Double lat = -23.4098268;
	private Double lng = -52.0612525;
	private CriticidadeProblema criticidade = CriticidadeProblema.BAIXA;
	private UsuarioId usuarioId = new UsuarioId("USUARIO_ID");
	private CidadeId cidadeId = new CidadeId("CIDADE_ID");
	private FotoPublicacaoId fotoPublicacaoId = new FotoPublicacaoId("FOTO_PUBLICACAO_ID");
	private PublicacaoId publicacaoId = new PublicacaoId();
	private String endereco = fotoBuilder.endereco;
	
	public PublicacaoBuilder titulo(String titulo){
		this.titulo = titulo;
		return this;
	}
	
	public PublicacaoBuilder descricao(String descricao){
		this.descricao = descricao;
		return this;
	}
	
	public PublicacaoBuilder categoria(Categoria categoria){
		this.categoria = categoria;
		return this;
	}
	
	public PublicacaoBuilder foto(String foto){
		this.foto = foto;
		return this;
	}
	
	public PublicacaoBuilder estado(String estado){
		this.estado = estado;
		return this;
	}
	
	public PublicacaoBuilder cidade(String cidade){
		this.cidade = cidade;
		return this;
	}
	
	public PublicacaoBuilder latitude(Double latitude){
		this.lat = latitude;
		return this;
	}
	
	public PublicacaoBuilder longitude(Double longitude){
		this.lng = longitude;
		return this;
	}
	
	public PublicacaoBuilder usuarioId(UsuarioId usuarioId){
		this.usuarioId = usuarioId;
		return this;
	}
	
	public PublicacaoBuilder cidadeId(CidadeId cidadeId){
		this.cidadeId = cidadeId;
		return this;
	}
	
	public PublicacaoBuilder fotoPublicacaoId(FotoPublicacaoId fotoPublicacaoId){
		this.fotoPublicacaoId = fotoPublicacaoId;
		return this;
	}
	
	public PublicacaoBuilder criticiade(CriticidadeProblema criticidadeProblema){
		this.criticidade = criticidadeProblema;
		return this;
	}
	
	public Publicacao criar(){
		SalvarPublicacaoCommand command = new SalvarPublicacaoCommand(titulo, descricao, categoria.name(), cidade, estado, 
				lat, lng, Arrays.asList(foto), criticidade);
		
		return new Publicacao(command, usuarioId, cidadeId, fotoPublicacaoId);
	}

	public FotoPublicacao criarFoto() {
		return new FotoPublicacao(fotoPublicacaoId, endereco);
	}

	public PublicacaoBuilder endereco(String endereco) {
		this.endereco = endereco;
		return this;
	}
}
