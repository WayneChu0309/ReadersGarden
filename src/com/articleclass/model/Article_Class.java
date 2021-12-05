package com.articleclass.model;

import java.io.Serializable;
public class Article_Class implements Serializable {
			private int ACID;
			private String CLASSNAME;
			
			public Article_Class() {
			}

			public Article_Class(int aCID, String cLASSNAME) {
				super();
				ACID = aCID;
				CLASSNAME = cLASSNAME;
			}

			public int getACID() {
				return ACID;
			}

			public void setACID(int aCID) {
				ACID = aCID;
			}

			public String getClassname() {
				return CLASSNAME;
			}

			public void setClassname(String cLASSNAME) {
				CLASSNAME = cLASSNAME;
			}
			
}
