package com.borrow.model;

import java.util.List;


public interface BorrowDAO {
	public abstract BorrowBean add(BorrowBean borrowBean);
	public abstract BorrowBean update(BorrowBean borrowBean);
	public abstract int notOrder(Integer stockId); // 找出被預訂 & 未歸還 1.2.3.8
	public abstract BorrowBean findByPk(Integer borrowId);
	
	// 檢查是否有預訂,預訂畫面用 states 1.8
	public abstract BorrowBean findByNumberPreOrder(Integer memberId, Integer stockId); 
	
	// 搜尋會員訂單,管理員用
	public abstract List<BorrowBean> findByNumberBorrow(Integer memberId); 
	
	// 會員全部訂單,會員評分用
	public abstract List<BorrowBean> findByNumberAllBorrow(Integer memberId);
	
	// 會員評分後, 計算平均分數
	public abstract Double findByStockScore(Integer stockId);
	
	//查詢所有評分紀錄
	public abstract List<BorrowBean> findAllByScore(Integer stockId);
	
	// 預訂訂單查詢 states 1 => 8
	public abstract List<BorrowBean> findByAllOrder();
	
	// 取消訂單查詢 states 6 => 7
	public abstract List<BorrowBean> findByAllCancel();
	
	// 所有逾期未還清單,管理員用
	public abstract List<BorrowBean> findAllOverDate(); // states 3
	// 會員逾期 or 取消訂單清單 3, 5, 6, 7
	public abstract List<BorrowBean> findByNumberIlleglBorrow(Integer memberId);
	// 會員逾期 or 取消訂單筆數 3, 5, 6, 7
	public abstract int getIlleglNumber(Integer memberId);
	
	// 會員正常歸還清單 4
	public abstract List<BorrowBean> findByNumberSuccessBorrow(Integer memberId);
	// 會員正常歸還筆數 4
	public abstract int getSuccessNumber(Integer memberId);
	
	
	/* 
	 * 	以下
	 *	 搜尋已借閱 / 逾期訂單 nowDate > preReDate & actReDate = null & states 2 => 3
	 *	 搜尋已預訂, 超過預訂時間未借閱訂單 nowDate > preBoDate & actBoDate = null & states 1,8 => 6
	 * 	 給排程用, 查無結果為空集合[], 非null
	 *  用 list.size() 判斷是否為0
	 */
	public abstract List<BorrowBean> findByOverDate(); 
	public abstract List<BorrowBean> findByPreOrderNoBorrow();
}
