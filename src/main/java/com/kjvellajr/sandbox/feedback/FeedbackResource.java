package com.kjvellajr.sandbox.feedback;

import com.kjvellajr.sandbox.jdo.PMF;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

@Path("/feedback")
public class FeedbackResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFeedback(@QueryParam("text") String pText) {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Feedback.class);
		q.setFilter("ownerEmail == ownerEmailParam");
		q.declareParameters("String ownerEmailParam");
		q.setUnique(true);
		Feedback feedback;
		final FeedbackLine feedbackLine = new FeedbackLine();
		try {
			feedback = (Feedback) q.execute("test@example.com");
			if (feedback == null) {
				feedback = new Feedback();
				feedback.setOwnerEmail("test@example.com");
				pm.makePersistent(feedback);
			}
			feedback.getFeedbackLines().add(feedbackLine);
			feedbackLine.setText(pText);
			pm.makePersistent(feedbackLine);
		} finally {
			q.closeAll();
			pm.close();
		}
		return Response.status(200).entity(feedback).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFeedback() {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Feedback.class);
		q.setFilter("ownerEmail == ownerEmailParam");
		q.declareParameters("String ownerEmailParam");
		q.setUnique(true);
		Feedback feedback;
		final FeedbackLine feedbackLine = new FeedbackLine();
		try {
			feedback = (Feedback) q.execute("test@example.com");
			if (feedback == null) {
				feedback = new Feedback();
				feedback.setOwnerEmail("test@example.com");
				pm.makePersistent(feedback);
			}
		} finally {
			q.closeAll();
			pm.close();
		}
		return Response.status(200).entity(feedback).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFeedback() {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Feedback.class);
		q.setFilter("ownerEmail == ownerEmailParam");
		q.declareParameters("String ownerEmailParam");
		q.setUnique(true);
		Feedback feedback;
		try {
			feedback = (Feedback) q.execute("test@example.com");
			if (feedback != null) {
				pm.deletePersistent(feedback);
			}
		} finally {
			q.closeAll();
			pm.close();
		}
		return Response.status(200).build();
	}
}
