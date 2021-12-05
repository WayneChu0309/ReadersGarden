package com.stock.model;

import java.sql.Date;
import java.util.List;

public class StockService {
	
	private StockDAO_interface dao;
	
	public StockService() {
		dao = new StockJNDIDAO();
	}
	
	public StockVO addStock(Integer typeId, String stockName, String author, String press, 
							Date issuedDate, String stockContent, double stockScore, byte[] stockImg) {
		StockVO stockVO = new StockVO();
		stockVO.setTypeId(typeId);
		stockVO.setStockName(stockName);
		stockVO.setAuthor(author);
		stockVO.setPress(press);
		stockVO.setIssuedDate(issuedDate);
		stockVO.setStockContent(stockContent);
		stockVO.setStockScore(stockScore);
		stockVO.setStockImg(stockImg);
		Integer stockId = dao.add(stockVO);
		stockVO.setStockId(stockId);
		return stockVO;
	}
	
	public Integer addStock2(StockVO vo) {
		return dao.add(vo);
	}
	
	public StockVO updateStock(StockVO vo) {
//		StockVO stockVO = new StockVO();
//		stockVO.setStockId(stockId);
//		stockVO.setTypeId(typeId);
//		stockVO.setStockName(stockName);
//		stockVO.setAuthor(author);
//		stockVO.setPress(press);
//		stockVO.setIssuedDate(issuedDate);
//		stockVO.setStockContent(stockContent);
//		stockVO.setStockScore(stockScore);
//		stockVO.setStockImg(stockImg);
//		dao.update(stockVO);
//		return stockVO;
		dao.update(vo);
		return vo;
	}
	
	public void deleteStock(Integer stockId) {
		dao.delete(stockId);
	}
	
	public StockVO getOneStock(Integer stockId) {
		return dao.findByStockId(stockId);
	}
	
	public List<StockVO> getAll(Integer typeId){
		return dao.getAll(typeId);
	}
	
	public Integer getLast(Integer stockId) {
		return dao.getLast(stockId);
	}
	
	public Integer getTypeTotal(Integer typeId) {
		return dao.findByTypeId(typeId);
	}
	
	public List<StockVO> getKeyword(String keyword) {
		return dao.getKeyword(keyword);
	}
}
