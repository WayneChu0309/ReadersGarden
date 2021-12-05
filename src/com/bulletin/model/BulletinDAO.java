package com.bulletin.model;

public interface BulletinDAO {
	public abstract BulletinBean add(BulletinBean buBean);
	public abstract BulletinBean findByPk(Integer bulletinId);
	public abstract BulletinBean findByNew();
}
