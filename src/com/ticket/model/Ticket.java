package com.ticket.model;

import java.io.Serializable;

public class Ticket implements Serializable {
		private int ORDERID;
		private int CUSTOMERID;
		private int ACTNO;
		private int TOTALCOST;
		private int ORDERDATE;
		
		public Ticket() {
		}

		public Ticket(int oRDERID, int cUSTOMERID, int aCTNO, int tOTALCOST, int oRDERDATE) {
			super();
			ORDERID = oRDERID;
			CUSTOMERID = cUSTOMERID;
			ACTNO = aCTNO;
			TOTALCOST = tOTALCOST;
			ORDERDATE = oRDERDATE;
		}

		public int getORDERID() {
			return ORDERID;
		}

		public void setORDERID(int oRDERID) {
			ORDERID = oRDERID;
		}

		public int getCUSTOMERID() {
			return CUSTOMERID;
		}

		public void setCUSTOMERID(int cUSTOMERID) {
			CUSTOMERID = cUSTOMERID;
		}

		public int getACTNO() {
			return ACTNO;
		}

		public void setACTNO(int aCTNO) {
			ACTNO = aCTNO;
		}

		public int getTOTALCOST() {
			return TOTALCOST;
		}

		public void setTOTALCOST(int tOTALCOST) {
			TOTALCOST = tOTALCOST;
		}

		public int getORDERDATE() {
			return ORDERDATE;
		}

		public void setORDERDATE(int oRDERDATE) {
			ORDERDATE = oRDERDATE;
		}
		
		
}
