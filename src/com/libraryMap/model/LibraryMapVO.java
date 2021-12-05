package com.libraryMap.model;

import java.io.Serializable;

public class LibraryMapVO implements Serializable {
	private Integer mapId;  // 流水號ID
	private Integer libId;
	private String floor;
	private byte[] floorImg;
	
	public LibraryMapVO() {}
	
	public LibraryMapVO(Integer mapId, Integer libId, String floor, byte[] floorImg) {
		super();
		this.mapId = mapId;
		this.libId = libId;
		this.floor = floor;
		this.floorImg = floorImg;
	}

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getLibraryId() {
		return libId;
	}

	public void setLibraryId(Integer libId) {
		this.libId = libId;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public byte[] getFloorImg() {
		return floorImg;
	}

	public void setFloorImg(byte[] floorImg) {
		this.floorImg = floorImg;
	}
	
}

