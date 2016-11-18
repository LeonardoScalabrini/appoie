package com.appoie.services;

import static com.appoie.utils.ValidationObject.isNull;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.dto.InformacoesUsuarioDTO;
import com.appoie.exceptions.CamposCadastrarException;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.EmailSenhaInvalidoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Cidade;
import com.appoie.models.Email;
import com.appoie.models.FotoPerfil;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.models.UsuarioFacebook;
import com.appoie.querys.UsuarioFacebookQuery;
import com.appoie.querys.UsuarioQuery;
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

	public InformacoesUsuarioDTO salvar(SalvarUsuarioFacebookCommand command, HttpSession session) throws Exception {
		UsuarioFacebook usuarioFacebook = null;
		Cidade cidade = null;
		
		//boolean primeiroAcesso = false;
		
		try {
			cidade = cidadeService.getCidade(command.nomeCidade, command.nomeEstado);
			usuarioFacebook = new UsuarioFacebook(command);
			usuarioFacebook.setCidadeId(cidade.getId());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		/*try {

			idCidade = usuarioFacebookquery.buscarCidadeId(command.getNomeCidade());
			usuarioFacebook = new UsuarioFacebook(command);
			usuarioFacebook.setCidadeId(idCidade);

		} catch (Exception e) {
			throw new CamposCadastrarException();
		}*/

		repository.save(usuarioFacebook);
		if (!usuarioQuery.existeEmail(new Email(command.email))) {
			//primeiroAcesso = true;
			criarUsuarioSistema(usuarioFacebook, cidade, session);
		} else {
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
			Sessao.setUsuarioId(id);
			Sessao.setCidadeId(cidadeId);
		}

		return usuarioQuery.buscarInformacoesDetalhadas(command.email);

	}

	public void criarUsuarioSistema(UsuarioFacebook usuarioFacebook, Cidade cidade, HttpSession session)
			throws Exception {
		UsuarioId usuarioId = usuarioFacebook.getId();
		Usuario usuario = new Usuario(usuarioId);
		Senha senha = new Senha(usuarioFacebook.getIdFacebook());
		usuario.setNome(usuarioFacebook.getNome());
		usuario.setSobrenome(usuarioFacebook.getSobrenome());
		usuario.setDataDeNascimento(usuarioFacebook.formatarData());
		usuario.setSexo(usuarioFacebook.getSexo());
		usuario.setEmail(usuarioFacebook.getEmail());
		usuario.setSenha(senha);
		usuario.setCidadeId(cidade.getId());
		FotoPerfil foto = new FotoPerfil(usuarioFacebook.getId(), usuarioFacebook.getFoto());
		fotoRepository.save(foto);
		usuarioRepository.save(usuario);
		//cidadeId = cidadeService.getCidadeIdUsuario(usuarioId);
		Sessao.setUsuarioId(usuarioId);
		Sessao.setCidadeId(cidade.getId());
	}

	// se for primeiro acesso vai retornar TRUE
	public boolean getSituacaoAcesso(String email) throws EmailFormatoException {
		if (!usuarioQuery.existeEmail(new Email(email))) return false;			
		else return true;
	}

}
