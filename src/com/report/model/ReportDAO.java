package com.report.model;

import java.util.List;

public interface ReportDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	void add(Report report);
	void update(Report report);
	void delete(int REPID);
	Report findByPK(int REPID);
	List<Report> getAll();
}

