package com.replyrecord.model;

import java.util.List;

public interface ReplyRecordDAO {
	void add(Reply_Record replyrecord);
	void update(Reply_Record replyrecord);
	void delete(int SENUM);
	Reply_Record findByPK(int SENUM);
	List<Reply_Record> getAll();
}
