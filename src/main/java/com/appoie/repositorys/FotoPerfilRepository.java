package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.ids.UsuarioId;
import com.appoie.models.FotoPerfil;

public interface FotoPerfilRepository extends JpaRepository<FotoPerfil, UsuarioId>  {

}
