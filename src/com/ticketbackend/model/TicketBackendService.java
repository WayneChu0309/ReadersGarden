package com.ticketbackend.model;

import java.util.List;
import java.util.Set;

public class TicketBackendService {


		private Ticket_BackendDAO dao;

		public TicketBackendService() {
			dao = new Ticket_BackendDAOImpl();
		}

		public Ticket_Backend addTicket_Backend(Integer totalnumt, Integer tleft, Integer tprice, Integer actid) {

			Ticket_Backend ticketbackend = new Ticket_Backend();
			ticketbackend.setTOTALNUMT(totalnumt);
			ticketbackend.setTLEFT(tleft);
			ticketbackend.setTPRICE(tprice);
			ticketbackend.setACTID(actid);
			dao.add(ticketbackend);
			return ticketbackend;
		}

		public Ticket_Backend updateTicket_Backend(Integer ticketid, Integer totalnumt, Integer tleft, Integer tprice, Integer actid) {

			Ticket_Backend ticketbackend = new Ticket_Backend();
			ticketbackend.setTICKETID(ticketid);
			ticketbackend.setTOTALNUMT(totalnumt);
			ticketbackend.setTLEFT(tleft);
			ticketbackend.setTPRICE(tprice);
			ticketbackend.setACTID(actid);
			dao.update(ticketbackend);

			return ticketbackend;
		}

		public void deleteTicket_Backend(Integer ticketid) {
			dao.delete(ticketid);
		}

		public Ticket_Backend getOneTicket_Backend(Integer ticketid) {
			return dao.findByPK(ticketid);
		}

		public List<Ticket_Backend> getAll() {
			return dao.getAll();
		}
}

