package br.com.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractModel{


	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)	
	private String email;

	@Column(nullable = false)
	private String password;


}
