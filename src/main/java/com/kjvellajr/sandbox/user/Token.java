package com.kjvellajr.sandbox.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {

	private String token;

	public String getToken() {
		return token;
	}
	public void setToken(String pToken) {
		token = pToken;
	}
}
