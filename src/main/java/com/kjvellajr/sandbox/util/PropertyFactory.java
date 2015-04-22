package com.kjvellajr.sandbox.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFactory {

	/**
	 * private constructor
	 */
	private PropertyFactory() {
	}

	public static Properties config() {
		Properties prop = new Properties();
		InputStream inputStream = PropertyFactory.class.getClassLoader().getResourceAsStream("config.properties");

		if (inputStream == null) {
			throw new RuntimeException("property file config.properties not found in the classpath");
		}
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return prop;
	}
}
