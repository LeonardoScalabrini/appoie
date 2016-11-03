package com.appoie.services;

import static com.appoie.utils.ValidationObject.isNull;
import static com.appoie.utils.ValidationString.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.CadastrarCommand;
import com.appoie.LoginUsuario;
import com.appoie.commands.AlterarEmailCommand;
import com.appoie.commands.AutenticarCommand;
import com.appoie.commands.AlterarSenhaCommand;
import com.appoie.commands.RecuperarSenhaCommand;
import com.appoie.dto.PerfilDTO;
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
import com.appoie.models.SenderMail;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.FotoPerfilRepository;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.utils.RandomAlphaNumeric;
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
			Cidade cidade = cidadeService.getCidade(command.cidade, command.estado);
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

	public void recuperarSenha(RecuperarSenhaCommand command)
			throws EmailFormatoException, EmailNaoCadastradoExcpetion {

		if (!usuarioQuery.existeEmail(new Email(command.email))) {
			throw new EmailNaoCadastradoExcpetion();
		}
		else {
			String senhaTemporaria = RandomAlphaNumeric.randomString(8);
			usuarioQuery.setPassword(command.email, senhaTemporaria);
			List<Email> destinarios = new ArrayList<>();
			destinarios.add(new Email(command.email));
			
			String mailBody = "<h2>Recuperação de senha</h2>"
					+ "<br><br><h4>Sua senha temporária é: " + senhaTemporaria
					+ "<br><br>Você deve entrar em sua conta o mais cedo possível para alterar sua senha! Obrigado!";
			SenderMail email = new SenderMail();
			email.sendMail(mailBody, "Recuperação de senha", destinarios);
			
		}
	}
	
	public void autenticar(AutenticarCommand command) throws EmailSenhaInvalidoException{
		Email email;
		Senha senha;
		UsuarioId id;
		CidadeId cidadeId;
		try {
			email = new Email(command.email);
			senha = new Senha(command.senha);
			id = usuarioQuery.buscar(email, senha);
			isNull(id);
			cidadeId = cidadeService.getCidadeIdUsuario(id);
			LoginUsuario.cidadeId = cidadeId;
			LoginUsuario.usuarioId = id;
		} catch (Exception e) {
			throw new EmailSenhaInvalidoException();
		}
		Sessao.setUsuarioId(id);
		Sessao.setCidadeId(cidadeId);
	}

	public void alterarPerfil(PerfilDTO perfilCommand) throws CamposCadastrarException {
		Usuario usuario = usuarioRepository.findOne(Sessao.getUsuarioId());
		FotoPerfil fotoPerfil = fotoPerfilRepository.findOne(Sessao.getUsuarioId());
		try {
			usuario.alterarPerfil(perfilCommand);
		} catch (Exception e) {
			throw new CamposCadastrarException();
		}
		usuarioRepository.save(usuario);
		fotoPerfilRepository.save(fotoPerfil);
	}

	public void alterarEmail(AlterarEmailCommand c, UsuarioId id) throws EmailFormatoException, CamposCadastrarException, NovoEmailIgualException {
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

	public void alterarSenha(AlterarSenhaCommand c, UsuarioId id) throws SenhaTamanhoMinimoException, Exception {
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

	public PerfilDTO getPerfil() {
		return usuarioQuery.getPerfil(Sessao.getUsuarioId());
	}
	
}
