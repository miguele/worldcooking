package org.worldcooking.server.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.worldcooking.server.entity.event.Event;

/**
 * Plain DAO which provides only
 * {@link org.worldcooking.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class EventDAOImpl extends GenericHibernateDAOImpl<Event, Long> {

	/**
	 * Method returning the event corresponding to the id parameter. <br/>
	 * The event is completed with all his objects.
	 * 
	 * @param id
	 *            Unique id use to retrieve the Event.
	 * @return The event. Return null if the event is not found.
	 */
	@SuppressWarnings("unchecked")
	public Event findFullEventById(Long id) {
		try {
			List<Event> eList = getHibernateTemplate().findByNamedParam(
					"from Event e join fetch e.availableTasks as t "
							+ "left join fetch e.subscriptions as s "
							+ "inner join fetch s.participants as p"
							+ " where e.id=:eventId", "eventId", id);
			if (eList != null && !eList.isEmpty()) {
				return eList.get(0);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method returning the event corresponding to the id parameter. <br/>
	 * The event is completed with all his objects.
	 * 
	 * @param id
	 *            Unique id use to retrieve the Event.
	 * @return The event. Return null if the event is not found.
	 */
	@SuppressWarnings("unchecked")
	public List<Event> findAllFullEvent() {
		try {
			List<Event> eList = getHibernateTemplate().find(
					"from Event e join fetch e.availableTasks as t "
							+ "left join fetch e.subscriptions as s "
							+ "left join fetch s.participants as p");
			return eList;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void resetDb() {
		List<Event> allEvents = findAll();
		getHibernateTemplate().deleteAll(allEvents);
	}

}
