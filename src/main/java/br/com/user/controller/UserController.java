package br.com.user.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import br.com.user.responseDTO.UserResponseDTO;

@RestController
@RequestMapping(path = "/user")
public class UserController{

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private ModelMapper modelMapper; 


	@GetMapping
	public ResponseEntity<List<User>> listUser(){
			return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") String id){
			UserResponseDTO userResponseDTO = this.modelMapper.map(this.userRepository.findById(id), UserResponseDTO.class);
			//return this.userRepository.findById(id).map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
