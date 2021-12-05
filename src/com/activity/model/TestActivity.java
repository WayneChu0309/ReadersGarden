package com.activity.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TestActivity {
	public static void main(String[] args) {
		
		ActivityDAO_interface dao = new ActivityDAO();
		ActivityVO act = new ActivityVO();
		
		// 新增
//		act.setActtype("讀書會");
//		dao.add(act);
		
		// 更新
//		act.setActtype("同樂會");
//		act.setActid(8);
//		
//		dao.update(act);
		
		// 刪除
//		dao.delete(8);
		
//		// 查詢單筆
//		Activity act1 = dao.findByPK(7);
//		System.out.print(act1.getActid());
//		System.out.print(act1.getActtype());
//		System.out.println();
//		System.out.println("==============================");
		
//		// 查詢多筆
		List <ActivityVO> act2 = dao.getAll();
		for(ActivityVO a : act2) {
			System.out.print(a.getActid());
			System.out.print(a.getActtype());
			System.out.println();
			System.out.println("==============================");
		}
	}
}
