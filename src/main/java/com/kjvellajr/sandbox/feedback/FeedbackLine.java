package com.kjvellajr.sandbox.feedback;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class FeedbackLine {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;

	@Persistent
	private Feedback feedback;

	@Persistent
	private String text;

	public Key getId() {
		return id;
	}
	public void setId(Key pId) {
		id = pId;
	}
	public String getText() {
		return text;
	}
	public void setText(String pText) {
		text = pText;
	}
}
