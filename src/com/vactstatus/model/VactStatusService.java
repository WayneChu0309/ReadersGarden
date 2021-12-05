package com.vactstatus.model;

import java.sql.Timestamp;
import java.util.List;

public class VactStatusService {
	private VactStatusDAO_Interface dao;
	
	public VactStatusService() {
		dao = new VactStatusDAO();
	}
	
	public VactStatusVO addVactStatus(Integer vactid, Integer statusid) {
		VactStatusVO vsVO = new VactStatusVO();
		vsVO.setVactid(vactid);
		vsVO.setStatusid(statusid);
		
		dao.add(vsVO);
		return vsVO;
	}
	
	public VactStatusVO updateVactStatus(Timestamp date, Integer listid, Integer vactid, Integer statusid) {
		VactStatusVO vsVO = new VactStatusVO();
		vsVO.setVactid(vactid);
		vsVO.setStatusid(statusid);
		vsVO.setDate(date);
		vsVO.setListid(listid);
		
		dao.update(vsVO);
		return vsVO;
	}
	
	public void deleteVactStatus(Integer listid) {
		dao.delete(listid);
	}
	
	public VactStatusVO findOneVactStatus(Integer listid) {
		return dao.findByPK(listid);
	}
	
	public List<VactStatusVO> getAll(){
		return dao.getAll();
	}
		
}
