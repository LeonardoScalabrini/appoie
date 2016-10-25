package com.appoie.services;

import static com.appoie.utils.ValidationObject.isNull;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.exceptions.CamposCadastrarException;
import com.appoie.exceptions.EmailSenhaInvalidoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Email;
import com.appoie.models.FotoPerfil;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.models.UsuarioFacebook;
import com.appoie.querys.UsuarioFacebookQuery;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.CidadeRepository;
import com.appoie.repositorys.FotoPerfilRepository;
import com.appoie.repositorys.UsuarioFacebookRepository;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.utils.Sessao;

@Service
public class UsuarioFacebookService {	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	@Autowired
	private UsuarioFacebookRepository repository;
	@Autowired
	private CidadeService cidadeService;
	@Autowired
	private UsuarioFacebookQuery usuarioFacebookquery;
	@Autowired
	private UsuarioQuery usuarioQuery;
	@Autowired
	private FotoPerfilRepository fotoRepository;
	

	public void salvar(SalvarUsuarioFacebookCommand command, HttpSession session) throws Exception {		
		UsuarioFacebook usuarioFacebook;
		CidadeId idCidade;
		try {
			
			idCidade = usuarioFacebookquery.buscarCidadeId(command.getNomeCidade());
			usuarioFacebook = new UsuarioFacebook(command);
			usuarioFacebook.setCidadeId(idCidade);
			
		} catch (Exception e) {
			throw new CamposCadastrarException();
		}
					
		repository.save(usuarioFacebook);
		if (!usuarioQuery.existeEmail(new Email(command.email))){
		   criarUsuarioSistema(usuarioFacebook, idCidade,session);
		}else{
			Email email;
			Senha senha = new Senha(usuarioFacebook.getIdFacebook());
			UsuarioId id;
			CidadeId cidadeId;
			try {
				email = new Email(command.email);
				senha = new Senha(command.idFacebook);
				id = usuarioQuery.buscar(email, senha);
				isNull(id);
				cidadeId = cidadeService.getCidadeIdUsuario(id);
			} catch (Exception e) {
				throw new EmailSenhaInvalidoException();
			}
			Sessao sessao = new Sessao(session);
			sessao.setUsuarioId(id);
			sessao.setCidadeId(cidadeId);
		}
			
		
	}
	
	public void criarUsuarioSistema(UsuarioFacebook usuarioFacebook, CidadeId cidadeId,HttpSession session) throws Exception {
		UsuarioId usuarioId = usuarioFacebook.getId();
		Usuario usuario = new Usuario(usuarioId);
		Senha senha = new Senha(usuarioFacebook.getIdFacebook());
		usuario.setNome(usuarioFacebook.getNome());
		usuario.setSobrenome(usuarioFacebook.getSobrenome());
		usuario.setDataDeNascimento(usuarioFacebook.formatarData());
		usuario.setSexo(usuarioFacebook.getSexo());
		usuario.setEmail(usuarioFacebook.getEmail());
		usuario.setSenha(senha);
		usuario.setCidadeId(cidadeId);
		usuarioRepository.save(usuario);
		FotoPerfil foto = new FotoPerfil(usuario.getId(), usuarioFacebook.getFoto());
		fotoRepository.save(foto);
		cidadeId = cidadeService.getCidadeIdUsuario(usuarioId);
		Sessao sessao = new Sessao(session);
		sessao.setUsuarioId(usuarioId);
		sessao.setCidadeId(cidadeId);
	}

	
	
}
