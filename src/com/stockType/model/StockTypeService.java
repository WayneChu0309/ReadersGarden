package com.stockType.model;

import java.util.List;

public class StockTypeService {
	
	private StockTypeDAO_interface dao;
	
	public StockTypeService() {
		dao = new StockTypeJNDIDAO();
	}
	
	public StockTypeVO addStockType(String typeName, String kind) {
		StockTypeVO stockTypeVO = new StockTypeVO();
		stockTypeVO.setTypeName(typeName);
		stockTypeVO.setKind(kind);
		dao.add(stockTypeVO);
		return stockTypeVO;
	}
	
	public StockTypeVO updateStockType(Integer typeId, String typeName, String kind) {
		StockTypeVO stockTypeVO = new StockTypeVO();
		stockTypeVO.setTypeId(typeId);
		stockTypeVO.setTypeName(typeName);
		stockTypeVO.setKind(kind);
		dao.add(stockTypeVO);
		return stockTypeVO;
	}
	
	public void deleteStockType(Integer typeId) {
		dao.delete(typeId);
	}
	
	public StockTypeVO getOneStockType(Integer typeId) {
		return dao.findByTypeId(typeId);
	}
	
	public List<StockTypeVO> getAll() {
		return dao.getAll();
	}
	
	public List<StockTypeVO> getBook() {
		return dao.getBook();
	}
	
	public List<StockTypeVO> getMovie() {
		return dao.getMovie();
	}
}
