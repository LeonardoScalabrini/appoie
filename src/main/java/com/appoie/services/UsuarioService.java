package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.RecuperarSenhaCommand;
import com.appoie.exceptions.CamposCadastrarException;
import com.appoie.exceptions.EmailCadastradoException;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.EmailNaoCadastradoExcpetion;
import com.appoie.maps.UsuarioMap;
import com.appoie.models.Email;
import com.appoie.models.SenderEmail;
import com.appoie.models.Usuario;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioQuery usuarioQuery;
	
	
	

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}
    @Autowired
	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void cadastrar(CadastrarCommand command) throws CamposCadastrarException, EmailCadastradoException {

		Usuario usuario;
		try {
			UsuarioMap usuarioMap = new UsuarioMap();
			usuario = usuarioMap.map(command);
		} catch (Exception e) {
			// TODO: handle exception
			throw new CamposCadastrarException();
		}

		if (!usuario.getEmail().getValue().equals(command.confirmarEmail))
			throw new CamposCadastrarException();

		if (usuarioQuery.existeEmail(usuario.getEmail()))
			throw new EmailCadastradoException();

		usuarioRepository.save(usuario);
		enviarEmailConfirmacaoCadastro(usuario.getEmail(), "Confirmação de cadastro");
	}

	public void recuperarSenha(RecuperarSenhaCommand command)
			throws EmailFormatoException, EmailNaoCadastradoExcpetion {

		if (!usuarioQuery.existeEmail(new Email(command.getEmail()))) {
			throw new EmailNaoCadastradoExcpetion();
		} else {

			String senhaRecuperada;
			// SenderEmail se = new SenderEmail(new Email(command.getEmail()),
			// "Recuperação de senha");

			senhaRecuperada = usuarioQuery.selectSenhaUsuarioByEmail(new Email(command.getEmail()));
			// se.sendSenha(senhaRecuperada);

		}

	}

	private void enviarEmailConfirmacaoCadastro(Email email, String subject) {
		SenderEmail se = new SenderEmail(email, subject);

		// se.sendConfirmacaoCadastro("<h1>OBRIGADO!</h1><br />Uma conta no
		// Appoie.com.br foi criada com este email (" + email.toString() + ")");

	}
	
	private void enviarEmailAlteracaoSenha(Email email, String subject) {
		SenderEmail se = new SenderEmail(email, subject);

		// se.sendConfirmacaoCadastro("<h1>ATENÇÃO!</h1><br />Houve uma alteração de senha de uma conta do
		// Appoie.com.br vinculada a este email (" + email.toString() + ")");

	}

}
