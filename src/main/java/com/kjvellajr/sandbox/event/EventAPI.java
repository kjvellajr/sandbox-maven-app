package com.kjvellajr.sandbox.event;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.kjvellajr.sandbox.jdo.PMF;
import com.kjvellajr.sandbox.model.Event;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/**
 * Resource to test RESTful api.
 * @author Ken Vella
 */
@Api(name = "eventApi", version = "v1", namespace = @ApiNamespace(
			ownerDomain = "sandbox.kjvellajr.com",
			ownerName = "sandbox.kjvellajr.com",
			packagePath = ""))
public class EventAPI {
	public class Events {
		private Collection<Event> events;
		public Events(Collection<Event> pEvents) {
			events = pEvents;
		}
		public Collection<Event> getEvents() {
			return events;
		}
		public void setEvents(Collection<Event> pEvents) {
			events = pEvents;
		}
	}
	@ApiMethod(name = "addEvent")
	public Event addEvent(@Named("description") String pDescription) {
		final Event e = new Event();
		// todo: create event
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			// todo: persist event			
		} finally {
			pm.close();
		}
		return e;
	}
	@ApiMethod(name = "getEvents")
	public Events getEvents() {
		final Collection<Event> events = new ArrayList<Event>();
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//todo: read
		} finally {
			pm.close();
		}
		return new Events(events);
	}
}

