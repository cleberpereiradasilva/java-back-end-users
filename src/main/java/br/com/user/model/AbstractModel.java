package br.com.user.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;


@MappedSuperclass
@Data
public abstract class AbstractModel{
	
	@Id
	private final String Id;
	private final Date createdAt;

	public AbstractModel(){
		this.Id = UUID.randomUUID().toString();
		this.createdAt = Calendar.getInstance().getTime();
	}

}
