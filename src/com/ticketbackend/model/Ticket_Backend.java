package com.ticketbackend.model;

import java.io.Serializable;

public class Ticket_Backend implements Serializable {
	private int TICKETID;
	private int TOTALNUMT;
	private int TLEFT;
	private int TPRICE;
	private int ACTID;
	
	public Ticket_Backend() {
	}

	public Ticket_Backend(int tICKETID, int tOTALNUMT, int tLEFT, int tPRICE, int aCTID) {
		super();
		TICKETID = tICKETID;
		TOTALNUMT = tOTALNUMT;
		TLEFT = tLEFT;
		TPRICE = tPRICE;
		ACTID = aCTID;
	}

	public int getTICKETID() {
		return TICKETID;
	}

	public void setTICKETID(int tICKETID) {
		TICKETID = tICKETID;
	}

	public int getTOTALNUMT() {
		return TOTALNUMT;
	}

	public void setTOTALNUMT(int tOTALNUMT) {
		TOTALNUMT = tOTALNUMT;
	}

	public int getTLEFT() {
		return TLEFT;
	}

	public void setTLEFT(int tLEFT) {
		TLEFT = tLEFT;
	}

	public int getTPRICE() {
		return TPRICE;
	}

	public void setTPRICE(int tPRICE) {
		TPRICE = tPRICE;
	}

	public int getACTID() {
		return ACTID;
	}

	public void setACTID(int aCTID) {
		ACTID = aCTID;
	}

}
