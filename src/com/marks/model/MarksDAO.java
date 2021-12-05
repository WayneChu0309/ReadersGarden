package com.marks.model;

import java.util.List;

public interface MarksDAO {
	
	public abstract MarksBean add(MarksBean marksBean);
	
	public abstract MarksBean update(MarksBean marksBean);
	
	public abstract MarksBean findByPk(Integer marksId);
	
	public abstract boolean findByMember(Integer memberId, Integer stockId);
	
	public abstract List<MarksBean> findMemberAll(Integer memberId);
	
}
