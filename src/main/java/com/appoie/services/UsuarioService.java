package com.appoie.services;

import static com.appoie.utils.ValidationObject.isNull;
import static com.appoie.utils.ValidationString.isNullOrEmpty;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.EmailCommand;
import com.appoie.commands.LoginCommand;
import com.appoie.commands.PerfilCommand;
import com.appoie.commands.SenhaCommand;
import com.appoie.commands.SenhaRecuperarCommand;
import com.appoie.exceptions.CamposCadastrarException;
import com.appoie.exceptions.EmailCadastradoException;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.EmailNaoCadastradoExcpetion;
import com.appoie.exceptions.EmailSenhaInvalidoException;
import com.appoie.exceptions.NovaSenhaIgualException;
import com.appoie.exceptions.NovoEmailIgualException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Cidade;
import com.appoie.models.Email;
import com.appoie.models.FotoPerfil;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.FotoPerfilRepository;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.utils.Sessao;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioQuery usuarioQuery;
	
	@Autowired
	private FotoPerfilRepository fotoPerfilRepository;
	
	@Autowired
	private CidadeService cidadeService;

	public void cadastrar(CadastrarCommand command) throws CamposCadastrarException, EmailCadastradoException {
		
		Usuario usuario;
		try {
			isNullOrEmpty(command.cidade);
			isNullOrEmpty(command.estado);
			Cidade cidade = cidadeService.recuperarCidade(command.cidade, command.estado);
			usuario = new Usuario(command, cidade.getId());
		} catch (Exception e) {
			throw new CamposCadastrarException();
		}

		if (!usuario.getEmail().getValue().equals(command.confirmarEmail))
			throw new CamposCadastrarException();
		
		if (!usuario.getSenha().getValue().equals(command.confirmarSenha))
			throw new CamposCadastrarException();
		
		if (usuarioQuery.existeEmail(usuario.getEmail()))
			throw new EmailCadastradoException();

		usuarioRepository.save(usuario);
	}

	public void recuperarSenha(SenhaRecuperarCommand command)
			throws EmailFormatoException, EmailNaoCadastradoExcpetion {

		if (!usuarioQuery.existeEmail(new Email(command.getEmail())))
			throw new EmailNaoCadastradoExcpetion();
	}
	
	public void autenticar(LoginCommand command, HttpSession session) throws EmailSenhaInvalidoException{
		Email email;
		Senha senha;
		UsuarioId id;
		CidadeId cidadeId;
		try {
			email = new Email(command.email);
			senha = new Senha(command.senha);
			id = usuarioQuery.buscar(email, senha);
			isNull(id);
			cidadeId = cidadeService.recuperarCidadeUsuario(id);
		} catch (Exception e) {
			throw new EmailSenhaInvalidoException();
		}
		Sessao sessao = new Sessao(session);
		sessao.setUsuarioId(id);
		sessao.setCidadeId(cidadeId);
	}

	public void alterarPerfil(PerfilCommand perfilCommand, UsuarioId id) throws CamposCadastrarException {
		Usuario usuario = usuarioRepository.findOne(id);
		FotoPerfil fotoPerfil = fotoPerfilRepository.findOne(id);
		try {
			usuario.alterarPerfil(perfilCommand);
		} catch (Exception e) {
			throw new CamposCadastrarException();
		}
		usuarioRepository.save(usuario);
		fotoPerfilRepository.save(fotoPerfil);
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
		return usuarioQuery.buscarPerfil(id);
	}
	
}
