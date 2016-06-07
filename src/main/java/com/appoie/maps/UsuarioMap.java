package com.appoie.maps;

import com.appoie.commands.CadastrarCommand;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;

public class UsuarioMap implements Map<Usuario, CadastrarCommand>{

	@Override
	public Usuario map(CadastrarCommand d) throws Exception {
		// TODO Auto-generated method stub
		return new Usuario(d.nome, 
				 		   d.sobrenome, 
				 		   d.dataDeNascimento, 
				 		   d.sexo, 
				 		   new Email(d.email), 
				 		   new Senha(d.senha));
	}

}
