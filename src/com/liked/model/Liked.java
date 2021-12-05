package com.liked.model;


import java.io.Serializable;
import java.sql.Date;
public class Liked implements Serializable {
		private int LIKEID;
		private int ACCTID;
		private int AID;	
		
		public Liked() {
		}

		public Liked(int lIKEID, int aCCTID, int aID) {
			super();
			LIKEID = lIKEID;
			ACCTID = aCCTID;
			AID = aID;
		}

		public int getLIKEID() {
			return LIKEID;
		}

		public void setLIKEID(int lIKEID) {
			LIKEID = lIKEID;
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
