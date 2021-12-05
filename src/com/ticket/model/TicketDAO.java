package com.ticket.model;

import java.util.List;

		public interface TicketDAO {
			// 此介面定義對資料庫的相關存取抽象方法
			void add(Ticket ticket1);
			void update(Ticket ticket1);
			void delete(int ORDERID);
			Ticket findByPK(int ORDERID);
			List<Ticket> getAll();

}
