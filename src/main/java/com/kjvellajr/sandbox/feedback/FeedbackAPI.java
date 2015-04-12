package com.kjvellajr.sandbox.feedback;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.kjvellajr.sandbox.api.Constants;
import com.kjvellajr.sandbox.jdo.PMF;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 * Resource to test RESTful api.
 * @author Ken Vella
 */
@Api(name = "feedback", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID})
public class FeedbackAPI {
	@ApiMethod(name = "addFeedback")
	public Feedback addFeedback(User pUser, @Named("text") String pText) throws UnauthorizedException {
		if (pUser == null) throw new UnauthorizedException("User is not authorized.");
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Feedback.class);
		q.setFilter("ownerEmail == ownerEmailParam");
		q.declareParameters("String ownerEmailParam");
		q.setUnique(true);
		Feedback feedback;
		final FeedbackLine feedbackLine = new FeedbackLine();
		try {
			feedback = (Feedback) q.execute(pUser.getEmail());
			if (feedback == null) {
				feedback = new Feedback();
				feedback.setOwnerEmail(pUser.getEmail());
				pm.makePersistent(feedback);
			}
			feedback.getFeedbackLines().add(feedbackLine);
			feedbackLine.setText(pText);
			pm.makePersistent(feedbackLine);
		} finally {
			q.closeAll();
			pm.close();
		}
		return feedback;
	}
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "getFeedback")
	public Feedback getFeedback(User pUser) throws UnauthorizedException {
		if (pUser == null) throw new UnauthorizedException("User is not authorized.");
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Feedback.class);
		q.setFilter("ownerEmail == ownerEmailParam");
		q.declareParameters("String ownerEmailParam");
		q.setUnique(true);
		Feedback feedback;
		try {
			feedback = (Feedback) q.execute(pUser.getEmail());
			if (feedback == null) {
				feedback = new Feedback();
				feedback.setOwnerEmail(pUser.getEmail());
				pm.makePersistent(feedback);
			}
		} finally {
			q.closeAll();
			pm.close();
		}
		return feedback;
	}
	@ApiMethod(name = "deleteFeedback")
	public void deleteFeedback(User pUser) throws UnauthorizedException {
		if (pUser == null) throw new UnauthorizedException("User is not authorized.");
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Feedback.class);
		q.setFilter("ownerEmail == ownerEmailParam");
		q.declareParameters("String ownerEmailParam");
		q.setUnique(true);
		Feedback feedback;
		try {
			feedback = (Feedback) q.execute(pUser.getEmail());
			if (feedback != null) {
				pm.deletePersistent(feedback);
			}
		} finally {
			q.closeAll();
			pm.close();
		}
	}
}

