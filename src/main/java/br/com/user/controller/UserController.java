package br.com.user.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;


import org.hibernate.engine.internal.Collections;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.user.respository.UserRepository;
import br.com.user.model.User;
import br.com.user.requestDTO.UserRequestDTO;
import br.com.user.responseDTO.UserResponseDTO;

@RestController
@RequestMapping(path = "/user")
public class UserController{

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private ModelMapper modelMapper; 


	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> listUser(){
                Type listType = new  TypeToken<List<UserResponseDTO>>() {}.getType();
			List<UserResponseDTO> listUserDTO = this.modelMapper.map(this.userRepository.findAll(), listType);
			return new ResponseEntity<>(listUserDTO, HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO user){
      
		User newUser = this.modelMapper.map(user, User.class);
		return new ResponseEntity<>(this.modelMapper.map(this.userRepository.save(newUser), UserResponseDTO.class), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") String id){
			UserResponseDTO userResponseDTO = this.modelMapper.map(this.userRepository.findById(id).get(), UserResponseDTO.class);
			return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<User> putUser(@PathVariable("id") String id, @RequestBody User user){
		return userRepository.findById(id).map(record -> {
			record.setName(user.getName());
			record.setEmail(user.getEmail());
			record.setPassword(user.getPassword());
			User userUpdated = this.userRepository.save(record);
			return new ResponseEntity<>(userUpdated, HttpStatus.OK);		
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> dropUser(@PathVariable("id") String id){
		return userRepository.findById(id).map(record -> {
			this.userRepository.deleteById(id);
			return new ResponseEntity<>("Item removed!", HttpStatus.OK);		
		}).orElse(ResponseEntity.notFound().build());
	}
}
