package com.replyrecord.model;

import java.io.Serializable;
import java.sql.Date;

public class Reply_Record implements Serializable {
	private int SENUM;
	private int ACCTID;
	private int AID;
	private String REPDESCRIPT;

	public Reply_Record() {
	}

	public Reply_Record(int sENUM, int aCCTID, int aID, String rEPDESCRIPT) {
		super();
		SENUM = sENUM;
		ACCTID = aCCTID;
		AID = aID;
		REPDESCRIPT = rEPDESCRIPT;
	}

	public int getSENUM() {
		return SENUM;
	}

	public void setSENUM(int sENUM) {
		SENUM = sENUM;
	}

	public int getACCTID() {
		return ACCTID;
	}

	public void setACCTID(int aCCTID) {
		ACCTID = aCCTID;
	}

	public int getAID() {
		return AID;
	}

	public void setAID(int aID) {
		AID = aID;
	}

	public String getREPDESCRIPT() {
		return REPDESCRIPT;
	}

	public void setREPDESCRIPT(String rEPDESCRIPT) {
		REPDESCRIPT = rEPDESCRIPT;
	}
	
}
