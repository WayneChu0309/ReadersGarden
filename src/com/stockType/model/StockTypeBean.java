package com.stockType.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.stock.model.StockBean;

@Entity
@Table(name = "STOCK_TYPE")
public class StockTypeBean implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TYPEID")
	@Expose
	private Integer typeId;

	@OneToMany(
			mappedBy = "typeBean",
			cascade = { CascadeType.PERSIST, CascadeType.MERGE }
	)
	
	private List<StockBean> stocks;
	
	@Column(name = "TYPENAME")
	@Expose
	private String typeName;

	@Column(name = "KIND")
	@Expose
	private String kind;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public List<StockBean> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockBean> stocks) {
		this.stocks = stocks;
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

	@Override
	public String toString() {
		return "StockTypeBean [typeId=" + typeId + ", typeName=" + typeName + ", kind=" + kind + "]";
	}


}
