package com.kjvellajr.sandbox.user;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Token {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String name;

	public Long getId() {
		return id;
	}
	public void setId(Long pId) {
		id = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String pName) {
		name = pName;
	}
}
