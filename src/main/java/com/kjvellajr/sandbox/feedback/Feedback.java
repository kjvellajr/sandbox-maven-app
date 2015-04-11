package com.kjvellajr.sandbox.feedback;

import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Feedback {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String ownerEmail;

	@Persistent(mappedBy = "parent")
	private List<FeedbackLine> feedbackLines;

	public Long getId() {
		return id;
	}
	public void setId(Long pId) {
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
	public void setFeedbackLine(List<FeedbackLine> pFeedbackLines) {
		feedbackLines = pFeedbackLines;
	}
}
