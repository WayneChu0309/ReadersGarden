package com.bulletin.model;

import java.util.List;

public interface BulletinDAO_interface {
	void add(BulletinVO bulletin);
	void update(BulletinVO bulletin);
	void delete(Integer bulletinId);
	List<String> test(); // test join
	BulletinVO findByBulletinId(Integer bulletinId);
	List<BulletinVO> getAll();
}
