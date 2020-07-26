package br.com.user.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
abstract class AbstractModel{
	
	@Id
	private final String Id;
	private final Date createdAt;
	private Date updatedAt;

	public AbstractModel(){
		this.Id = UUID.randomUUID().toString();
		this.createdAt = Calendar.getInstance().getTime();
	}

}
