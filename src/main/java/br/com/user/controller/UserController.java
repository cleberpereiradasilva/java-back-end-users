package br.com.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController{

	@GetMapping
	public ResponseEntity<String> listUser(){
			return new ResponseEntity<>("Ola mundo", HttpStatus.OK);
	}

}
