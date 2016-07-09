package com.appoie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.models.FotoPerfil;
import com.appoie.repositorys.FotoPerfilRepository;

@RestController
@RequestMapping("/fotoPerfil")
public class FotoPerfilController {
	@Autowired
	private FotoPerfilRepository fotoRepository;
	
	@RequestMapping(method =RequestMethod.POST)
	private void realizarUpload(@RequestBody FotoPerfil fotoPerfil){
		fotoRepository.save(fotoPerfil);
	}
}
