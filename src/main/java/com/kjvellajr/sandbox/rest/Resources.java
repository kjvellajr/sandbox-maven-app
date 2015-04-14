package com.kjvellajr.sandbox.rest;

import com.kjvellajr.sandbox.feedback.FeedbackResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class Resources extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(FeedbackResource.class);
		return s;
	}
}
