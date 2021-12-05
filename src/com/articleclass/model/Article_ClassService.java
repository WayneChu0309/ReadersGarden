package com.articleclass.model;

import java.util.List;
import java.util.Set;

public class Article_ClassService {


			private Article_ClassDAO dao;

			public Article_ClassService() {
				dao = new Article_ClassDAOImpl();
			}

			public Article_Class addArticle(String classname) {

				Article_Class article = new Article_Class();

				article.setClassname(classname);
				dao.add(article);
				return article;
			}

			public Article_Class updateArticle(Integer acid, String classname) {

				Article_Class article = new Article_Class();

				article.setACID(acid);
				article.setClassname(classname);
				
				dao.update(article);

				return article;
			}

			public void deleteArticle(Integer acid) {
				dao.delete(acid);
			}

			public Article_Class getOneArticle(Integer acid) {
				return dao.findByPK(acid);
			}

			public List<Article_Class> getAll() {
				return dao.getAll();
			}
}

