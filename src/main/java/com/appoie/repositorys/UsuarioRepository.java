package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.models.Usuario;
import com.appoie.models.UsuarioId;

public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioId>{

}
