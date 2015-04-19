package com.kjvellajr.sandbox.user;

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

@Path("/auth")
public class AuthResource {

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("userName") String pUserName, @QueryParam("password") String pPassword) {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query qUser = pm.newQuery(User.class);
		qUser.setFilter("name == nameParam");
		qUser.declareParameters("String nameParam");
		qUser.setUnique(true);
		final Query qToken = pm.newQuery(Token.class);
		qToken.setFilter("name == nameParam");
		qToken.declareParameters("String nameParam");
		qToken.setUnique(true);
		User user;
		Token token;
		int status = 200;
		try {
			user = (User) qUser.execute(pUserName);
			if (user == null) {
				return Response.status(500).entity("User not found!").build();
			}
			token = (Token) qToken.execute(pUserName);
			if (token == null) {
				status = 201;
				token = new Token();
				token.setName(pUserName);
				pm.makePersistent(token);
			}
		} finally {
			qUser.closeAll();
			qToken.closeAll();
			pm.close();
		}
		return Response.status(status).entity(token).build();
	}
}
