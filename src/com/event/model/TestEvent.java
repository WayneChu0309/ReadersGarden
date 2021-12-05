package com.event.model;

import java.util.List;

import com.article.model.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestEvent {
	
	public static void main(String[] args) throws ParseException {
		
		EventDAO_interface dao = new EventDAO();
		Event event = new Event();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse("2021-11-19 19:20:00");
		long timeLong = time.getTime();
		java.sql.Date time1 = new java.sql.Date(timeLong);
		// 新增
//		event.setEventid(14);
//		event.setEventcateid(2);
//		event.setMemberid(3);
//		event.setCapacity(4);
//		event.setEventname("讀書");
//		event.setEventdescription("徵人一起看書");
//		event.setEventstart(time1);
//		event.setEventend(time1);
//		event.setEventappstart(time1);
//		event.setEventappend(time1);
//		event.setEventstatus(0);
//		dao.add(event);
		
		// 修改
//		event.setEventid(14);
//		event.setEventcateid(3);
//		event.setMemberid(3);
//		event.setCapacity(3);
//		event.setEventname("打球");
//		event.setEventdescription("徵人一起打球");
//		event.setEventstart(time1);
//		event.setEventend(time1);
//		event.setEventappstart(time1);
//		event.setEventappend(time1);
//		event.setEventstatus(1);
//		dao.update(event);
		
		// 刪除
//		dao.delete(14);
		
//		// 查詢單筆
//		Event evt1 = dao.findByPK(1);
//		System.out.print(evt1.getEventid() + ",");
//		System.out.print(evt1.getEventcateid() + ",");
//		System.out.print(evt1.getMemberid() + ",");
//		System.out.print(evt1.getCapacity() + ",");
//		System.out.print(evt1.getEventname() + ",");
//		System.out.print(evt1.getEventdescription() + ",");
//		System.out.print(evt1.getEventstart() + ",");
//		System.out.print(evt1.getEventend() + ",");
//		System.out.print(evt1.getEventappstart() + ",");
//		System.out.print(evt1.getEventappend() + ",");
//		System.out.print(evt1.getEventstatus() + ",");
//		System.out.println("---------------------");
////
////		// 查詢多筆
//		List <Event> list = dao.getAll();
//		for (Event evt2 : list) {
//		System.out.print(evt2.getEventid() + ",");
//		System.out.print(evt2.getEventcateid() + ",");
//		System.out.print(evt2.getMemberid() + ",");
//		System.out.print(evt2.getCapacity() + ",");
//		System.out.print(evt2.getEventname() + ",");
//		System.out.print(evt2.getEventdescription() + ",");
//		System.out.print(evt2.getEventstart() + ",");
//		System.out.print(evt2.getEventend() + ",");
//		System.out.print(evt2.getEventappstart() + ",");
//		System.out.print(evt2.getEventappend() + ",");
//		System.out.print(evt2.getEventstatus() + ",");
//		System.out.println("--------------------");
		
		// 查詢單筆
		List <Event> list = dao.findByNew();
		System.out.println(list);
//		for (Event evt3 : list) {
//		System.out.print(evt3.getEventid() + ",");
//		System.out.print(evt3.getEventcateid() + ",");
//		System.out.print(evt3.getMemberid() + ",");
//		System.out.print(evt3.getCapacity() + ",");
//		System.out.print(evt3.getEventname() + ",");
//		System.out.print(evt3.getEventdescription() + ",");
//		System.out.print(evt3.getEventstart() + ",");
//		System.out.print(evt3.getEventend() + ",");
//		System.out.print(evt3.getEventappstart() + ",");
//		System.out.print(evt3.getEventappend() + ",");
//		System.out.print(evt3.getEventstatus() + ",");
//		System.out.println("---------------------");
//
//		}
	}
}
