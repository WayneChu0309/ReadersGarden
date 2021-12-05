package com.ticket.model;

import java.util.List;
import java.util.Set;

public class TicketService {


		private TicketDAO dao;

		public TicketService() {
			dao = new TicketDAOImpl();
		}

		public Ticket addTicket(Integer totalcost, Integer actno, Integer orderdate) {

			Ticket ticket = new Ticket();
			ticket.setTOTALCOST(totalcost);
			ticket.setACTNO(actno);
			ticket.setORDERDATE(orderdate);
			dao.add(ticket);
			return ticket;
		}

		public Ticket updateTicket(Integer orderid, Integer totalcost, Integer actno, Integer orderdate) {

			Ticket ticket = new Ticket();
			ticket.setORDERID(orderid);
			ticket.setTOTALCOST(totalcost);
			ticket.setACTNO(actno);
			ticket.setORDERDATE(orderdate);
			dao.update(ticket);

			return ticket;
		}

		public void deleteTicket(Integer orderid) {
			dao.delete(orderid);
		}

		public Ticket getOneTicket(Integer orderid) {
			return dao.findByPK(orderid);
		}

		public List<Ticket> getAll() {
			return dao.getAll();
		}
}

