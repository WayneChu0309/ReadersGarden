package com.stockList.model;

import java.util.List;

public interface StockListDAO {
	public abstract StockListBean add(StockListBean listBean);
	public abstract StockListBean update(StockListBean listBean);
	 // 找出可預訂的數量 listStates = 1
	public abstract int availStock(Integer stockId);
	 // 找出可預訂的listId
	public abstract int findByAvailListId(Integer stockId);
	public abstract StockListBean findByListId(Integer listId);
	 // 找該筆清單
	public abstract List<StockListBean> findByAvailList(Integer stockId);
	 // 相同 stockId 的清單
	public abstract List<StockListBean> findByAllList(Integer stockId);
}
