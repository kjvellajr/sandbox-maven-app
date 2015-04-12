package com.kjvellajr.sandbox.feedback;

import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Feedback {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;

	@Persistent
	private String ownerEmail;

	@Persistent(mappedBy = "feedback")
	@Element(dependent = "true")
	private List<FeedbackLine> feedbackLines = new ArrayList<FeedbackLine>();

	public Key getId() {
		return id;
	}
	public void setId(Key pId) {
		id = pId;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String pOwnerEmail) {
		ownerEmail = pOwnerEmail;
	}
	public List<FeedbackLine> getFeedbackLines() {
		return feedbackLines;
	}
	public void setFeedbackLines(List<FeedbackLine> pFeedbackLines) {
		feedbackLines = pFeedbackLines;
	}
}
