package com.article.model;

import java.io.Serializable;
import java.sql.Date;
public class Article implements Serializable {
		@Override
	public String toString() {
		return "Article [AID=" + AID + ", ACID=" + ACID + ", ACCTID=" + ACCTID + ", ANAME=" + ANAME + ", ADESCRIPT="
				+ ADESCRIPT + ", APD=" + APD + "]";
	}

		private int AID;
		private int ACID;
		private int ACCTID;
		private String ANAME;
		private String ADESCRIPT;
		private Date APD;
		
		public Article() {
		}

		public Article(int aID, int aCID, int aCCTID, String aNAME, String aDESCRIPT, Date aPD) {
			super();
			AID = aID;
			ACID = aCID;
			ACCTID = aCCTID;
			ANAME = aNAME;
			ADESCRIPT = aDESCRIPT;
			APD = aPD;
		}

		public int getAID() {
			return AID;
		}

		public void setAID(int aID) {
			AID = aID;
		}

		public int getACID() {
			return ACID;
		}

		public void setACID(int aCID) {
			ACID = aCID;
		}

		public int getACCTID() {
			return ACCTID;
		}

		public void setACCTID(int aCCTID) {
			ACCTID = aCCTID;
		}

		public String getANAME() {
			return ANAME;
		}

		public void setANAME(String aNAME) {
			ANAME = aNAME;
		}

		public String getADESCRIPT() {
			return ADESCRIPT;
		}

		public void setADESCRIPT(String aDESCRIPT) {
			ADESCRIPT = aDESCRIPT;
		}

		public Date getAPD() {
			return APD;
		}

		public void setAPD(Date aPD) {
			APD = aPD;
		}
		
}