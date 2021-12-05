package com.report.model;

import java.util.List;
import java.util.Set;

public class ReportService {


		private ReportDAO dao;

		public ReportService() {
			dao = new ReportDAOImpl();
		}

		public Report addReport(Integer acctid, Integer aid) {

			Report report = new Report();
			report.setACCTID(acctid);
			report.setAID(aid);
			dao.add(report);
			return report;
		}

		public Report updateReport(Integer repid, Integer acctid, Integer aid) {

			Report report = new Report();
			report.setREPID(repid);
			report.setACCTID(acctid);
			report.setAID(aid);
			dao.update(report);

			return report;
		}

		public void deleteReport(Integer repid) {
			dao.delete(repid);
		}

		public Report getOneReport(Integer repid) {
			return dao.findByPK(repid);
		}

		public List<Report> getAll() {
			return dao.getAll();
		}
}

