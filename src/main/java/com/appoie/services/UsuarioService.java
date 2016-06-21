package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.EmailCommand;
import com.appoie.commands.LoginCommand;
import com.appoie.commands.PerfilCommand;
import com.appoie.commands.SenhaCommand;
import com.appoie.exceptions.CamposCadastrarException;
import com.appoie.exceptions.EmailCadastradoException;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.EmailSenhaInvalidoException;
import com.appoie.exceptions.NovaSenhaIgualException;
import com.appoie.exceptions.NovoEmailIgualException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.models.Email;
import com.appoie.models.Foto;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.models.UsuarioId;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.FotoRepository;
import com.appoie.repositorys.UsuarioRepository;
import static com.appoie.utils.ValidationString.*;

import static com.appoie.utils.ValidationObject.*;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioQuery usuarioQuery;
	
	@Autowired
	private FotoRepository fotoRepository;

	public void cadastrar(CadastrarCommand command) throws CamposCadastrarException, EmailCadastradoException {
		Usuario usuario;
		try {
			usuario = new Usuario(command);
		} catch (Exception e) {
			throw new CamposCadastrarException();
		}
		if(!usuario.getEmail().getValue().equals(command.confirmarEmail))
			throw new CamposCadastrarException();
			
		if(usuarioQuery.existeEmail(usuario.getEmail()))
			throw new EmailCadastradoException();

		usuarioRepository.save(usuario);
	}
	
	public UsuarioId realizarLogin(LoginCommand command) throws EmailSenhaInvalidoException{
		Email email;
		Senha senha;
		UsuarioId id;
		try {
			email = new Email(command.email);
			senha = new Senha(command.senha);
			id = usuarioQuery.buscar(email, senha);
			isNull(id);
		} catch (Exception e) {
			throw new EmailSenhaInvalidoException();
		}
		return id;
	}

	public void alterarPerfil(PerfilCommand perfilCommand, UsuarioId id) throws CamposCadastrarException {
		Usuario usuario = usuarioRepository.findOne(id);
		Foto foto = fotoRepository.findOne(id);
		try {
			usuario.alterarPerfil(perfilCommand);
			foto.data = perfilCommand.foto;
		} catch (Exception e) {
			throw new CamposCadastrarException();
		}
		usuarioRepository.save(usuario);
		fotoRepository.save(foto);
	}

	public void alterarEmail(EmailCommand c, UsuarioId id) throws EmailFormatoException, CamposCadastrarException, NovoEmailIgualException {
		Usuario usuario = usuarioRepository.findOne(id);
		try{
			isNullOrEmpty(c.emailAtual);
			isNullOrEmpty(c.novoEmail);
			isNullOrEmpty(c.confirmarNovoEmail);
		}catch (Exception e) {
			throw new CamposCadastrarException();
		}
		if(!c.novoEmail.equals(c.confirmarNovoEmail)){
			throw new CamposCadastrarException();
		}
		Email email = usuario.getEmail();
		if(email.getValue().equals(c.novoEmail)){
			throw new NovoEmailIgualException();
		}
		email.setValue(c.novoEmail);
		usuarioRepository.save(usuario);
	}

	public void alterarSenha(SenhaCommand c, UsuarioId id) throws SenhaTamanhoMinimoException, Exception {
		Usuario usuario = usuarioRepository.findOne(id);
		try {
			isNullOrEmpty(c.senhaAtual);
			isNullOrEmpty(c.novaSenha);
			isNullOrEmpty(c.confirmarNovaSenha);
		} catch (Exception e) {
			throw new CamposCadastrarException();
		}
		if (!c.novaSenha.equals(c.confirmarNovaSenha)){
			throw new CamposCadastrarException();
		}
		Senha senha = usuario.getSenha();
		if(senha.getValue().equals(c.novaSenha)){
			throw new NovaSenhaIgualException();
		}
		senha.setSenha(c.novaSenha);
		usuarioRepository.save(usuario);
	}

	public PerfilCommand buscarPerfil(UsuarioId id) {
		Usuario usuario = usuarioRepository.findOne(id);
		Foto foto = fotoRepository.findOne(id);
		return new PerfilCommand(usuario, foto);
	}
	
}
