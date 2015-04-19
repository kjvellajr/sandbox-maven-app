package com.kjvellajr.sandbox.api;

import com.kjvellajr.sandbox.feedback.FeedbackResource;
import com.kjvellajr.sandbox.user.AuthResource;
import com.kjvellajr.sandbox.user.UserResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class Resources extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(AuthResource.class);
		s.add(FeedbackResource.class);
		s.add(UserResource.class);
		return s;
	}
}
