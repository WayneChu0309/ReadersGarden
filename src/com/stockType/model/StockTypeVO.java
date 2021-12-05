package com.stockType.model;

import java.io.Serializable;

public class StockTypeVO implements Serializable{
	private Integer typeId;
	private String typeName;
	private String kind;
	
	public StockTypeVO() {}

	public StockTypeVO(Integer typeId, String typeName, String kind) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.kind = kind;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
}
