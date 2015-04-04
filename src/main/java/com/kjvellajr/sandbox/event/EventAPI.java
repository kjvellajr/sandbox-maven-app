package com.kjvellajr.sandbox.event;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.kjvellajr.sandbox.jdo.PMF;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import javax.jdo.PersistenceManager; 
import javax.jdo.Query;

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

	@ApiMethod(name = "getEvents")
	public Events getEvents() {
		final Collection<Event> events = new ArrayList<Event>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery("select from Event");
		try {
			List<Event> results = (List<Event>) q.execute();
			if (!results.isEmpty()) {
				for (Event e : results) {
					events.add(e);
				}
			}
		} finally {
			pm.close();
		}
		return new Events(events);
	}

	@ApiMethod(name = "addEvent")
	public Events addEvent(@Named("name") String pName) {
		final Collection<Event> events = new ArrayList<Event>();
		final Event event = new Event();
		event.setDisplayName(pName);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(event);
		} finally {
			pm.close();
		}
		events.add(event);
		return new Events(events);
	}
}

