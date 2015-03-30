package com.kjvellajr.sandbox.tester;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Resource to test RESTful api.
 * @author Ken Vella
 */
@Path("/helloworld")
public class HelloWorldResource {
	@GET
	@Produces("application/xml")
	public String main() {
		return "<test>Hello World, V2!</test>";
	}
}