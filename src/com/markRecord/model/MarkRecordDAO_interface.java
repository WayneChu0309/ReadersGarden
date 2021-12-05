package com.markRecord.model;

import java.util.List;



public interface MarkRecordDAO_interface {
	
	public	void add(MarkRecordVO markRecord);
	public	void update(MarkRecordVO markRecord);
	public	void delete(Integer serialNumber);
	public MarkRecordVO findByPK(Integer serialNumber);
	List<MarkRecordVO> getAll();
}
