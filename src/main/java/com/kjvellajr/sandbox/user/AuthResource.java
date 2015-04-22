package com.kjvellajr.sandbox.user;

import com.kjvellajr.sandbox.jdo.PMF;
import com.kjvellajr.sandbox.util.PropertyFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.security.SecureRandom;
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

	private String key = null;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("userName") String pUserName, @QueryParam("password") String pPassword) {
		if (key == null) {
			key = PropertyFactory.config().getProperty("secret");
		}
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query qUser = pm.newQuery(User.class);
		qUser.setFilter("name == nameParam");
		qUser.declareParameters("String nameParam");
		qUser.setUnique(true);
		User user;
		Token token;
		int status = 200;
		try {
			user = (User) qUser.execute(pUserName);
			if (user == null) {
				return Response.status(500).entity("User not found!").build();
			}
			token = new Token();
			token.setToken(Jwts.builder().setSubject(user.getName()).signWith(SignatureAlgorithm.HS256, key).compact());
		} finally {
			qUser.closeAll();
			pm.close();
		}
		return Response.status(status).entity(token).build();
	}
}
