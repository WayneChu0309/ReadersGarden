package com.replyrecord.model;

import java.util.List;
import java.util.Set;

public class ReplyRecordService {


		private ReplyRecordDAO dao;

		public ReplyRecordService() {
			dao = new ReplyRecordDAOImpl();
		}

		public Reply_Record addReplyRecord(Integer acctid, Integer aid, String repdescript) {

			Reply_Record replyRecord = new Reply_Record();
			replyRecord.setACCTID(acctid);
			replyRecord.setAID(aid);
			replyRecord.setREPDESCRIPT(repdescript);
			dao.add(replyRecord);
			return replyRecord;
		}

		public Reply_Record updateReplyRecord(Integer senum, Integer acctid, Integer aid, String repdescript) {

			Reply_Record replyRecord = new Reply_Record();
			replyRecord.setSENUM(senum);
			replyRecord.setACCTID(acctid);
			replyRecord.setAID(aid);
			replyRecord.setREPDESCRIPT(repdescript);
			dao.update(replyRecord);

			return replyRecord;
		}

		public void deleteReplyRecord(Integer senum) {
			dao.delete(senum);
		}

		public Reply_Record getOneReplyRecord(Integer senum) {
			return dao.findByPK(senum);
		}

		public List<Reply_Record> getAll() {
			return dao.getAll();
		}
}


