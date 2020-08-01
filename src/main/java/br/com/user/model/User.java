package br.com.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractModel{


    @NotNull(message = "Field name cannot be empty.")
	private String name;

	@Column(unique = true, nullable = false)	
	private String email;

	@Column(nullable = false)
	private String password;


}
