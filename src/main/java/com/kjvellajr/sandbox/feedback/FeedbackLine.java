package com.kjvellajr.sandbox.feedback;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class FeedbackLine {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Feedback parent;

	public Long getId() {
		return id;
	}
	public void setId(Long pId) {
		id = pId;
	}
	public Feedback getParent() {
		return parent;
	}
	public void setParent(Feedback pParent) {
		parent = pParent;
	}
}
