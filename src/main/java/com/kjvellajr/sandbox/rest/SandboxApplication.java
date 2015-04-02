package com.kjvellajr.sandbox.rest;

import com.kjvellajr.sandbox.tester.HelloWorldResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class SandboxApplication extends Application {
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attachDefault(HelloWorldResource.class);
		return router;
	}
}

