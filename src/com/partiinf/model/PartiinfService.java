package com.partiinf.model;

import java.util.List;

public class PartiinfService {

	private PartiinfDAO_interface dao;

	public PartiinfService() {
		dao = new PartiinfDAO();
	}

	public Partiinf addParti(Integer eventid, Integer memberid) {

		Partiinf partiinf = new Partiinf();

		partiinf.seteventid(eventid);
		partiinf.setmemberid(memberid);
		dao.add(partiinf);

		return partiinf;
	}

	public Partiinf updateParti(Integer eventid, Integer memberid) {

		Partiinf partiinf = new Partiinf();

		partiinf.seteventid(eventid);
		partiinf.setmemberid(memberid);
		dao.update(partiinf);

		return partiinf;
	}

	public void deletePartiinf(Integer eventid) {
		dao.delete(eventid);
	}

	public Partiinf getOnePartiinf(Integer eventid) {
		return dao.findByPK(eventid);
	}

	public List<Partiinf> getAll() {
		return dao.getAll();
	}
	
	public Integer findByJoin(Integer eventid) {
		return dao.findByJoin(eventid);
	}
	
	public List<Partiinf> findByMember(Integer memberid){
		return dao.findByMember(memberid);
	}
	
}
