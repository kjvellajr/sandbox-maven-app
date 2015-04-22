package com.kjvellajr.sandbox.user;

import com.kjvellajr.sandbox.jdo.PMF;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

@Path("/user")
public class UserResource {

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query qUser = pm.newQuery(User.class);
		qUser.setFilter("id == idParam");
		qUser.declareParameters("Long idParam");
		qUser.setUnique(true);
		User user;
		try {
			user = (User) qUser.execute(Long.parseLong(id, 10));
			if (user == null) {
				return Response.status(404).build();
			}
		} finally {
			qUser.closeAll();
			pm.close();
		}
		return Response.ok(user).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query qUser = pm.newQuery(User.class);
		qUser.setFilter("id == idParam");
		qUser.declareParameters("Long idParam");
		qUser.setUnique(true);
		User user;
		try {
			user = (User) qUser.execute(Long.parseLong(id, 10));
			if (user == null) {
				return Response.status(404).build();
			}
			pm.deletePersistent(user);
		} finally {
			qUser.closeAll();
			pm.close();
		}
		return Response.status(200).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User pUser) {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query qUser = pm.newQuery(User.class);
		qUser.setFilter("name == nameParam");
		qUser.declareParameters("String nameParam");
		qUser.setUnique(true);
		User user;
		int status;
		try {
			user = (User) qUser.execute(pUser.getName());
			if (user == null) {
				status = 201;
				pUser.setId(null);
				user = pm.makePersistent(pUser);
			} else {
				status = 200;
			}
		} finally {
			qUser.closeAll();
			pm.close();
		}
		return Response.status(status)
			.header(
				"Location",
				String.format("%s/%s", uriInfo.getAbsolutePath().toString(),
					user.getId()))
			.entity(user)
			.build();
	}
}
