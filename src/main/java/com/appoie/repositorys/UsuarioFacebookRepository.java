package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.ids.UsuarioId;
import com.appoie.models.UsuarioFacebook;

public interface UsuarioFacebookRepository extends JpaRepository<UsuarioFacebook, UsuarioId> {

}
