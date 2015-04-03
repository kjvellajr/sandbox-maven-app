package com.kjvellajr.sandbox.tester;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import javax.inject.Named;

/**
 * Resource to test RESTful api.
 * @author Ken Vella
 */
@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(
			ownerDomain = "sandbox.kjvellajr.com",
			ownerName = "sandbox.kjvellajr.com",
			packagePath = ""))
public class HelloWorldAPI {
	public class Message {
		private String message;
		public Message(String msg) {
			message = msg;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String pMessage) {
			message = pMessage;
		}
	}
	@ApiMethod(name = "sayHi")
	public Message sayHi(@Named("name") String pName) {
		return new Message("Hi, " + pName + "!");
	}
}

