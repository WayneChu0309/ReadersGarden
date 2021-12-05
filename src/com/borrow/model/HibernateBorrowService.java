package com.borrow.model;

import java.util.List;

public class HibernateBorrowService {
	private BorrowDAO dao;
	
	public HibernateBorrowService(BorrowDAO dao) {
		this.dao = dao;
	}
	
	public BorrowBean add(BorrowBean borrowBean) {
		return dao.add(borrowBean);
	}
	
	public BorrowBean update(BorrowBean borrowBean) {
		return dao.update(borrowBean);
	} 
	
	public int notOrder(Integer stockId) {
		return dao.notOrder(stockId);
	}
	
	public BorrowBean findByPk(Integer borrowId) {
		return dao.findByPk(borrowId);
	}
	
	public BorrowBean findByNumberPreOrder(Integer memberId, Integer stockId) {
		return dao.findByNumberPreOrder(memberId, stockId);
	}
	
	public List<BorrowBean> findByNumberBorrow(Integer memberId) {
		return dao.findByNumberBorrow(memberId);
	}
	
	public List<BorrowBean> findByNumberAllBorrow(Integer memberId) {
		return dao.findByNumberAllBorrow(memberId);
	}
	
	
	public List<BorrowBean> findAllOverDate() {
		return dao.findAllOverDate();
	}
	
	public List<BorrowBean> findByOverDate(){
		return dao.findByOverDate();
	}
	public List<BorrowBean> findByPreOrderNoBorrow(){
		return dao.findByPreOrderNoBorrow();
	}
	
	public List<BorrowBean> findByNumberIlleglBorrow(Integer memberId) {
		return dao.findByNumberIlleglBorrow(memberId);
	}
	
	public int getIlleglNumber(Integer memberId) {
		return dao.getIlleglNumber(memberId);
	}
	
	public List<BorrowBean> findByNumberSuccessBorrow(Integer memberId) {
		return dao.findByNumberSuccessBorrow(memberId);
	}
	
	public int getSuccessNumber(Integer memberId) {
		return dao.getSuccessNumber(memberId);
	}
	
	public List<BorrowBean> findByAllOrder() {
		return dao.findByAllOrder();
	}
	
	public List<BorrowBean> findByAllCancel() {
		return dao.findByAllCancel();
	}
	
	public Double findByStockScore(Integer stockId) {
		return dao.findByStockScore(stockId);
	}
	
	public List<BorrowBean> findAllByScore(Integer stockId) {
		return dao.findAllByScore(stockId);
	}
	
}
