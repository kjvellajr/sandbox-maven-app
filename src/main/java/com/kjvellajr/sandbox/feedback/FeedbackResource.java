package com.kjvellajr.sandbox.feedback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.logging.Logger;

@Path("/feedback")
public class FeedbackResource {

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFeedbackData() {
		return new String("hello world!");
	}
}
