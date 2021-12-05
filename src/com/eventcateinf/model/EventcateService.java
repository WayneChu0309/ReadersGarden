package com.eventcateinf.model;

import java.sql.Date;
import java.util.List;

public class EventcateService {

	private EventcateDAO_interface dao;

	public EventcateService() {
		dao = new EventcateDAO();
	}

	public Eventcate addEventcate(Integer eventcateid, String eventcatename) {

		Eventcate eventcate = new Eventcate();

		eventcate.seteventcateid(eventcateid);
		eventcate.seteventcatename(eventcatename);
		dao.add(eventcate);

		return eventcate;
	}

	public Eventcate updateEventcate(Integer eventcateid, String eventcatename) {

		Eventcate eventcate = new Eventcate();

		eventcate.seteventcateid(eventcateid);
		eventcate.seteventcatename(eventcatename);
		dao.update(eventcate);

		return eventcate;
	}

	public void deleteEventcate(Integer eventcateid) {
		dao.delete(eventcateid);
	}

	public Eventcate getOneEventcate(Integer eventcateid) {
		return dao.findByPK(eventcateid);
	}

	public List<Eventcate> getAll() {
		return dao.getAll();
	}
}
