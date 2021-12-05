package com.liked.model;

import java.util.List;

public interface LikedDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	void add(Liked liked);
	void update(Liked liked);
	void delete(int LIKEID);
	Liked findByPK(int LIKEID);
	List<Liked> getAll();
}
