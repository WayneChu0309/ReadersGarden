package com.articleclass.model;

import java.util.List;

public interface Article_ClassDAO {
	void add(Article_Class article_class);
	void update(Article_Class article_class);
	void delete(int ACID);
	Article_Class findByPK(int ACID);
	List<Article_Class> getAll();
}

