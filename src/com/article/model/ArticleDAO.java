package com.article.model;

import java.util.List;

public interface ArticleDAO {
	void add(Article article);
	void update(Article article);
	void delete(int aID);
	Article findByPK(int aID);
	List<Article> getAll();
}
