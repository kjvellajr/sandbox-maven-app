package com.kjvellajr.sandbox.event;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.kjvellajr.sandbox.jdo.PMF;
import com.kjvellajr.sandbox.model.Event;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 * Resource to test RESTful api.
 * @author Ken Vella
 */
@Api(name = "eventApi", version = "v1", namespace = @ApiNamespace(
	ownerDomain = "sandbox.kjvellajr.com",
	ownerName = "sandbox.kjvellajr.com", packagePath = ""))
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
	public Event addEvent(@Named("displayName") String pDisplayName) {
		final Event e = new Event();
		e.setDisplayName(pDisplayName);
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(e);
		} finally {
			pm.close();
		}
		return e;
	}
	@ApiMethod(name = "deleteEvent")
	public void deleteEvent(@Named("id") Long pId) {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Event e = pm.getObjectById(Event.class, pId);
			pm.deletePersistent(e);
		} finally {
			pm.close();
		}
	}
	@ApiMethod(name = "getEvents")
	public Events getEvents() {
		final Collection<Event> events = new ArrayList<Event>();
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query q = pm.newQuery(Event.class);
		try {
			List<Event> results = (List<Event>) q.execute();
			if (!results.isEmpty()) {
				for (Event e : results) {
					events.add(e);
				}
			}
		} finally {
			q.closeAll();
			pm.close();
		}
		return new Events(events);
	}
}

