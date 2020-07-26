package br.com.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
public class User extends AbstractModel{


	@NonNull
	private String name;

	@NonNull
	private String email;

	@NonNull	
	private String password;


}
