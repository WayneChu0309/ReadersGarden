package com.report.model;

import java.io.Serializable;
import java.sql.Date;
public class Report implements Serializable {
		private int REPID;
		private int ACCTID;
		private int AID;	
		
		public Report() {
		}

		public Report(int rEPID, int aCCTID, int aID) {
			super();
			REPID = rEPID;
			ACCTID = aCCTID;
			AID = aID;
		}

		public int getREPID() {
			return REPID;
		}

		public void setREPID(int rEPID) {
			REPID = rEPID;
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
		
		
}
