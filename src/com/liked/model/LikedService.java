package com.liked.model;

import java.util.List;
import java.util.Set;

public class LikedService {


		private LikedDAO dao;

		public LikedService() {
			dao = new LikedDAOImpl();
		}

		public Liked addLiked(Integer acctid, Integer aid) {

			Liked liked = new Liked();
			liked.setACCTID(acctid);
			liked.setAID(aid);
			dao.add(liked);
			return liked;
		}

		public Liked updateLiked(Integer likeid, Integer acctid, Integer aid) {

			Liked liked = new Liked();
			liked.setLIKEID(likeid);
			liked.setACCTID(acctid);
			liked.setAID(aid);
			dao.update(liked);

			return liked;
		}

		public void deleteLiked(Integer likeid) {
			dao.delete(likeid);
		}

		public Liked getOneLiked(Integer likeid) {
			return dao.findByPK(likeid);
		}

		public List<Liked> getAll() {
			return dao.getAll();
		}
}
