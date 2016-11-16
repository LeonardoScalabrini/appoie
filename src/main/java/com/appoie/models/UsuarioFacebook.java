package com.appoie.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
@Entity
public class UsuarioFacebook extends BasicEntity<UsuarioId> {	   
    @AttributeOverride(name="calendar",column=@Column(name="data_acesso_facebook"))
    private Calendar calendar = Calendar.getInstance();
    private String idFacebook;	    
	private String nome;
    @Transient
	private String sobrenome;
    @Transient		
	private String dataDeNascimento;
    @Transient
	@Enumerated(EnumType.STRING)
	public Sexo sexo;
    @Transient
	public Email email;	
    @Transient
	private String nomeCidade;
    @Transient
    private String foto;	    
    @Transient
	private CidadeId cidadeId;
	
	public UsuarioFacebook(UsuarioId id, String idFacebook, String nome, String sobrenome,String dataDeNascimento, Sexo sexo,
			Email email, String nomeCidade, String foto) {
		this();
		this.idFacebook = idFacebook;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento =dataDeNascimento;
		this.sexo = sexo;
		this.email = email;
		this.nomeCidade = nomeCidade;
		this.foto =foto;
	}
	
	public UsuarioFacebook(SalvarUsuarioFacebookCommand command) throws EmailFormatoException {
		this();
		setIdFacebook(command.idFacebook);
		setNome(command.nome);
		setSobrenome(command.sobrenome);
		setDataDeNascimento(command.dataDeNascimento);
		setSexo(command.sexo);
		setEmail(new Email(command.email));
		setNomeCidade(command.getNomeCidade());
		setFoto(command.foto);
	
	}
	
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public UsuarioFacebook() {
		super(new UsuarioId());
	}
	
	public String getIdFacebook() {
		return idFacebook;
	}
	
	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Email getEmail() {
		return email;
	}
	
	public void setEmail(Email email) {
		this.email = email;
	}
	
	public String getNomeCidade() {
		return nomeCidade;
	}
	
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
	public CidadeId getCidadeId() {
		return cidadeId;
	}
	
	public void setCidadeId(CidadeId cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Calendar formatarData() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(this.dataDeNascimento));
		return calendar;	
	}

}
