
package br.com.user.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.user.model.AbstractModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends AbstractModel{

	private String name;

	private String email;


}


