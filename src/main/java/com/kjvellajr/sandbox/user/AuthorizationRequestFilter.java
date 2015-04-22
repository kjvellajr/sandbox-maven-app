package com.kjvellajr.sandbox.user;

import com.kjvellajr.sandbox.util.PropertyFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {

	private static final String LOGIN_URI = "auth";
	private static final String CREATE_USER_URI = "user";
	private static final String BEARER = "Bearer";
	private String key = null;

	@Override
	public void filter(ContainerRequestContext pRequestContext) {
		if (LOGIN_URI.equals(pRequestContext.getUriInfo().getPath())
				|| CREATE_USER_URI.equals(pRequestContext.getUriInfo().getPath())) {
			return;
		}
		if (key == null) {
			key = PropertyFactory.config().getProperty("secret");
		}
		final String authHeader = pRequestContext.getHeaderString("Authorization");
		String type = null;
		String token = null;
		if (authHeader == null) {
			pRequestContext.abortWith(Response.status(401).build());
		} else {
			final String[] split = authHeader.split(" ");
			type = split[0];
			token = split[1];
			if (!BEARER.equals(type)) {
				pRequestContext.abortWith(Response.status(401).build());
			}
		}
		try {
			Jwts.parser().setSigningKey(key).parse(token);
		} catch (SignatureException e) {
			pRequestContext.abortWith(Response.status(403).build());
		} catch (Exception e) {
			pRequestContext.abortWith(Response.status(400).build());
		}
	}
}
