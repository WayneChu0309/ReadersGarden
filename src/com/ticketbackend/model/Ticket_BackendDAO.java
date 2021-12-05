package com.ticketbackend.model;

import java.util.List;
//import ticketBackend.Ticket_Backend;


public interface Ticket_BackendDAO {
				// 此介面定義對資料庫的相關存取抽象方法
		void add(Ticket_Backend ticket_backend);
		void update(Ticket_Backend ticket_backend);
		void delete(int TICKETID);
		Ticket_Backend findByPK(int TICKETID);
		List<Ticket_Backend> getAll();

}

