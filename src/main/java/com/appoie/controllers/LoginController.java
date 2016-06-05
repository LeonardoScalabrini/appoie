package com.appoie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.LoginCommand;
import com.appoie.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method=RequestMethod.POST)
	public void realizarLogin(@RequestBody LoginCommand loginCommand) throws Exception{
		loginService.realizarLogin(loginCommand);
	}
	
}
