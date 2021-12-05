package com.marks.model;

import java.util.List;

public class HibernateMarksService {
	private MarksDAO dao;
	public HibernateMarksService(MarksDAO dao) {
		this.dao = dao;
	}
	
	public MarksBean add(MarksBean marksBean) {
		return dao.add(marksBean);
	}
	
	public MarksBean update(MarksBean marksBean) {
		return dao.update(marksBean);
	}
	
	public MarksBean findByPk(Integer marksId) {
		return dao.findByPk(marksId);
	}
	
	public List<MarksBean> findMemberAll(Integer memberId) {
		return dao.findMemberAll(memberId);
	}
	
	public boolean findByMember(Integer memberId, Integer stockId) {
		return dao.findByMember(memberId, stockId);
	}
	
	
}
