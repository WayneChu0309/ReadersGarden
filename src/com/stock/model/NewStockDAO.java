package com.stock.model;

import java.util.List;

public interface NewStockDAO {
	public abstract List<NewStock> findNew();
	public abstract void update(NewStock newstock);
	public abstract NewStock findByPk(Integer newId);

}
