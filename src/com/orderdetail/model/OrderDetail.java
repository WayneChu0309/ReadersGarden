package com.orderdetail.model;

import java.io.Serializable;
import java.sql.Date;

public class OrderDetail implements Serializable {
		private int TICKETID;
		private int ORDERID;
		private int ACTNO;
			
		public OrderDetail() {
		}

		public OrderDetail(int tICKETID, int oRDERID, int aCTNO) {
			super();
			TICKETID = tICKETID;
			ORDERID = oRDERID;
			ACTNO = aCTNO;
		}

			public int getTICKETID() {
				return TICKETID;
			}

			public void setTICKETID(int tICKETID) {
				TICKETID = tICKETID;
			}

			public int getORDERID() {
				return ORDERID;
			}

			public void setORDERID(int oRDERID) {
				ORDERID = oRDERID;
			}

			public int getACTNO() {
				return ACTNO;
			}

			public void setACTNO(int aCTNO) {
				ACTNO = aCTNO;
			}
}
			
