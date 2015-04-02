package com.kjvellajr.sandbox.tester;

import com.google.gson.Gson;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource to test RESTful api.
 * @author Ken Vella
 */
public class HelloWorldResource extends ServerResource {
	final Gson gson = new Gson();
	public class Message {
		private String message;
		public Message(String msg) {
			message = msg;
		}
	}
	@Get
	public String main() {
		return gson.toJson(new Message("Hello World!"));
	}
}

