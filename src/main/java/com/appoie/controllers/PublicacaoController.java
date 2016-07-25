package com.appoie.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.commands.PublicacaoRecuperarCommand;
import com.appoie.exceptions.NumeroFotosPublicacaoInvalido;
import com.appoie.exceptions.PublicacaoNaoEncontradaException;
import com.appoie.ids.PublicacaoId;
import com.appoie.services.PublicacaoService;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	PublicacaoService service;

	@RequestMapping(method = RequestMethod.PUT, value = "/editar")
	public void editar(@RequestBody PublicacaoEditarCommand command, HttpSession session) {
		try {
			service.editar(command, session);
		} catch (PublicacaoNaoEncontradaException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/recuperar")
	public List<PublicacaoRecuperarCommand> recuperar() {
		return service.recuperar();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public void salvar(@RequestBody PublicacaoCommand command, HttpSession session) {
		try {
			service.salvar(command, session);
		} catch (NumeroFotosPublicacaoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/excluir")
	public void deletar(@PathVariable PublicacaoId id, HttpSession session) {
		try {
			service.excluir(id, session);
		} catch (PublicacaoNaoEncontradaException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/image", method = RequestMethod.GET, produces = "image/jpg")
	public @ResponseBody String getFile() {
	try {
	
		File file = new File("C:/fotos/publicacao/67a22832-1738-4f48-8503-4ffb12fff0ad/9dc916c0-ddb5-4984-ad54-797d2b9ca78f.jpg");
	    BufferedImage img = ImageIO.read(file);

	    ByteArrayOutputStream bao = new ByteArrayOutputStream();

	    ImageIO.write(img, "jpg", bao);
	    
	    return Base64.getEncoder().encodeToString(bao.toByteArray());
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
	}
}
