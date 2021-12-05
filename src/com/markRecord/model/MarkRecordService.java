package com.markRecord.model;


import java.util.List;

import com.markRecord.model.MarkRecordVO;
import com.markRecord.model.*;

public class MarkRecordService {
	
	
	private MarkRecordDAO_interface dao;

	public MarkRecordService() {
		dao = new MarkRecordDAO();
	}

	public MarkRecordVO addMarkRecord( Integer number, Integer stockID, Integer vacID) {
	    MarkRecordVO markRecordVO = new MarkRecordVO();

		markRecordVO.setNumber(number);
		markRecordVO.setStockID(stockID);
		markRecordVO.setVacID(vacID);
		

		dao.add(markRecordVO);

		return markRecordVO;
	}

	public MarkRecordVO updateMarkRecord(Integer serialNumber, Integer number, Integer stockID, Integer vacID) {

		MarkRecordVO markRecordVO = new MarkRecordVO();

		markRecordVO.setNumber(number);
		markRecordVO.setStockID(stockID);
		markRecordVO.setVacID(vacID);
		markRecordVO.setSerialNumber(serialNumber);
		dao.update(markRecordVO);


		return markRecordVO;
	}

	public void deleteMarkRecord(Integer serialNumber) {
		dao.delete(serialNumber);
	}
	
	public MarkRecordVO getOneMarkRecord(Integer serialNumber) {
		return dao.findByPK(serialNumber);
	}

	public List<MarkRecordVO> getAll() {
		return dao.getAll();
	}
}


