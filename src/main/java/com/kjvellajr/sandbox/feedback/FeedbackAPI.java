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
	clientIds = {Constants.WEB_CLIENT_ID},
	audiences = {Constants.WEB_CLIENT_ID},
	namespace = @ApiNamespace(
		ownerDomain = "sandbox.kjvellajr.com",
		ownerName = "sandbox.kjvellajr.com", packagePath = ""))
public class FeedbackAPI {
	@ApiMethod(name = "addFeedback")
	public Feedback addFeedback(User pUser, @Named("text") String pText) throws Exception {
		if (pUser == null)  {
			throw new UnauthorizedException("unauthorized");
		}
		final Feedback feedback = new Feedback();
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(feedback);
		} finally {
			pm.close();
		}
		return feedback;
	}
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "getFeedback")
	public Feedback getFeedback(User pUser) throws Exception {
		if (pUser == null)  {
			throw new UnauthorizedException("unauthorized");
		}
		final Feedback feedback = new Feedback();
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Feedback.class);
		try {
			Feedback result = (Feedback) q.execute();
			if (result != null) {
			}
		} finally {
			q.closeAll();
			pm.close();
		}
		return feedback;
	}
}

