package com.borrow.model;

import java.util.List;

public interface BorrowDAO_interface {
	void add(BorrowVO borrow);
	void update(BorrowVO borrow);
	void delete(Integer borrowId);
	Integer notOrder(Integer stockId);  // 借閱訂單扣掉已歸還的數量 計算剩餘可借數量
	BorrowVO findByBorrowId(Integer borrowId); 
	BorrowVO findByNumberPreOrder(Integer memberId, Integer stockId); // 查詢會員是否有預訂訂單
	List<BorrowVO> getAll();
}
