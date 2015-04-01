package com.kjvellajr.sandbox.tester;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Resource to test RESTful api.
 * @author Ken Vella
 */
@Path("/helloworld")
public class HelloWorldResource {
	final Gson gson = new Gson();
	public class Message {
		private String message;
		public Message(String msg) {
			message = msg;
		}
	}
	@GET
	@Produces("application/json")
	public String main() {
		return gson.toJson(new Message("Hello World!"));
	}
}

