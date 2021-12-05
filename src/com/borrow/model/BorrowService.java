package com.borrow.model;

import java.sql.Date;
import java.util.List;

public class BorrowService {
	
	private BorrowDAO_interface dao;
	
	public BorrowService() {
		dao = new BorrowJNDIDAO();
	}
	
	public BorrowVO addBorrow(Integer memberId, Integer stockId, Integer listId, Date preBoDate, Date actBoDate, Date preReDate, 
								Date actReDate, String scoreContent, Date scoreDate, Integer score, Integer boStates) {
		BorrowVO borrowVO = new BorrowVO();
		borrowVO.setMemberId(memberId);
		borrowVO.setStockId(stockId);
		borrowVO.setListId(listId);
		borrowVO.setPreBoDate(preBoDate);
		borrowVO.setActBoDate(actBoDate);
		borrowVO.setPreReDate(preReDate);
		borrowVO.setActReDate(actReDate);
		borrowVO.setScoreContent(scoreContent);
		borrowVO.setScoreDate(scoreDate);
		borrowVO.setScore(score);
		borrowVO.setBoStates(boStates);
		dao.add(borrowVO);
		return borrowVO;
	}
	
	public BorrowVO updateBorrow(Integer borrowId, Integer memberId, Integer stockId, Integer listId, Date preBoDate, 
									Date actBoDate, Date preReDate, Date actReDate, String scoreContent, 
									Date scoreDate, Integer score, Integer boStates) {
		
		BorrowVO borrowVO = new BorrowVO();
		borrowVO.setBorrowId(borrowId);
		borrowVO.setMemberId(memberId);
		borrowVO.setStockId(stockId);
		borrowVO.setListId(listId);
		borrowVO.setPreBoDate(preBoDate);
		borrowVO.setActBoDate(actBoDate);
		borrowVO.setPreReDate(preReDate);
		borrowVO.setActReDate(actReDate);
		borrowVO.setScoreContent(scoreContent);
		borrowVO.setScoreDate(scoreDate);
		borrowVO.setScore(score);
		borrowVO.setBoStates(boStates);
		dao.update(borrowVO);
		return borrowVO;
	}
	
	public void deleteBorrow(Integer borrowId) {
		dao.delete(borrowId);
	}
	
	public BorrowVO getOneBorrow(Integer borrowId) {
		return dao.findByBorrowId(borrowId);
	}
	
	public List<BorrowVO> getAll() {
		return dao.getAll();
	}
	
	public Integer getNoOrder(Integer stockId) {
		return dao.notOrder(stockId);
	}
	
	public BorrowVO getPreOrder(Integer memberId, Integer stockId) {
		return dao.findByNumberPreOrder(memberId, stockId);
	}

}
