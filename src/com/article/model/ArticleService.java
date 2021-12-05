package com.article.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class ArticleService {


		private ArticleDAO dao;

		public ArticleService() {
			dao = new ArticleDAOImpl();
		}

		public Article addArticle(Integer acid, Integer acctid, String aname,
				String adescript) {

			Article article = new Article();

			article.setACID(acid);
			article.setACCTID(acctid);
			article.setANAME(aname);
			article.setADESCRIPT(adescript);
			dao.add(article);
			return article;
		}

		public Article updateArticle(Integer aid, Integer acid, Integer acctid, String aname,
				String adescript) {

			Article article = new Article();
			article.setAID(aid);
			article.setACID(acid);
			article.setACCTID(acctid);
			article.setANAME(aname);
			article.setADESCRIPT(adescript);
			dao.update(article);
			

			return article;
		}

		public void deleteArticle(Integer aid) {
			dao.delete(aid);
		}

		public Article getOneArticle(Integer aid) {
			return dao.findByPK(aid);
		}

		public List<Article> getAll() {
			return dao.getAll();
		}
	}




